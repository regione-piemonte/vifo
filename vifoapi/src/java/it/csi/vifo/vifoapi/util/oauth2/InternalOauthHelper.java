/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util.oauth2;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.util.OkHttpClientHolder;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InternalOauthHelper extends BaseApiServiceImpl {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	public static final String ACCESS_TOKEN = "access_token";
	public static final String OAUTH2_GRANT_TYPE = "grantType";
	public static final String OAUTH2_GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";

	/**
	 * @param oauthURL       l'endpoint del servizio REST /api/token
	 * @param consumerKey    consumerKey @see <a href=
	 *                       "https://docs.wso2.com/display/AM191/Token+API">consumerKey</a>
	 * @param consumerSecret consumerSecret @see <a href=
	 *                       "https://docs.wso2.com/display/AM191/Token+API">consumerSecret</a>
	 * @param timeout        timeout in millisecondi della connessione verso
	 *                       /api/token
	 */
	public InternalOauthHelper(String oauthURL, String consumerKey, String consumerSecret, int timeout) {
		this.oauthURL = (oauthURL != null) ? oauthURL.trim() : oauthURL;
		this.consumerKey = (consumerKey != null) ? consumerKey.trim() : consumerKey;
		this.consumerSecret = (consumerSecret != null) ? consumerSecret.trim() : consumerSecret;
		this.timeout = timeout;
	}

	/**
	 * @param oauthURL       l'endpoint del servizio REST /api/token
	 * @param consumerKey    consumerKey @see <a href=
	 *                       "https://docs.wso2.com/display/AM191/Token+API">consumerKey</a>
	 * @param consumerSecret consumerSecret @see <a href=
	 *                       "https://docs.wso2.com/display/AM191/Token+API">consumerSecret</a>
	 */

	public InternalOauthHelper(String oauthURL, String consumerKey, String consumerSecret) {
		this(oauthURL, consumerKey, consumerSecret, 30000);
	}

	private String oauthURL;
	private String consumerKey;
	private String consumerSecret;
	private int timeout;
	private long EXPIRY_LIMIT = 60000;

	private static volatile String token = "dummytoken";

	private static volatile Long expires = new Long(0);

	private static volatile long hashCount; // # accessi token in cache
	private static volatile long counter; // # richieste token

	/**
	 * 
	 * @return il tempo di expires del token in cache
	 */
	public static long getExpires() {
		if (expires != null)
			return expires.longValue();
		return 0;
	}

	/**
	 * 
	 * @return il token da usare verso l'API Manager
	 *         <p>
	 *         Se esiste in cache viene tornato l'ultimo token ottenuto dall'API
	 *         Manager altrimenti si effettua la richiesta e si torna il valore
	 *         ottenuto
	 */
	public String getToken() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (token != null && expires.longValue() > EXPIRY_LIMIT) {
			hashCount++;
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,getClassFunctionDebugString(CLASSNAME, methodName,"----- 1b")));
			return token; // use cache if exist
		} else {
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,getClassFunctionDebugString(CLASSNAME, methodName,"----- 2b")));
			return getNewToken();
		}
	}

	/**
	 * 
	 * @return il token da usare verso l'API Manager
	 *         <p>
	 *         Forza l'ottenimento del token dall'API Manager. Il valore ritornato
	 *         viene tenuto in cache locale per soddisfare successive
	 *         <code>getToken</code> Usare questo metodo solo se e' noto a priori
	 *         che il valore in cache e' invalido
	 * 
	 */
	private String getNewToken() {
		token = null; // invalidate cache
		return getTokenInternal();
	}

	/**
	 * Implementa la connessione verso l'API Manager
	 * 
	 * @return il token ottenuto dall'API Manager
	 */
	private String getTokenInternal() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"[OauthHelper.getTokenInternal]\n" + "oauthURL ......: " + oauthURL + "\n" + "consumerKey ...: "
				+ consumerKey + "\n" + "consumerSecret : " + Util.maskForLog(consumerSecret) + "\n"
				+ "timeout .......: " + timeout));
		
		LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 1c"));

		OkHttpClient client = OkHttpClientHolder.getInstance(timeout);

		RequestBody formBody = new FormBody.Builder().add("grant_type", "client_credentials")
				.add("client_id", consumerKey).add("client_secret", consumerSecret).build();
		LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 2c"));
		Request request = new Request.Builder().url(this.oauthURL).post(formBody).build();
		LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 3c"));
		String responseBodyString = null;
		try {
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 4c"));
			Response tokenResponse = client.newCall(request).execute();
			if (!tokenResponse.isSuccessful()) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,
						"wrong HTTP status code: " + tokenResponse.code() + "\nStatusLine:" + tokenResponse.message()));
				token = null;
				expires = null;
				LOGGER.warn(getClassFunctionDebugString(CLASSNAME, methodName, "OAUTH2 token set to null"));
				return token;
			}
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 5c"));
			// Read the response body.
			responseBodyString = tokenResponse.body().string();
			GetTokenResponse responseBody = new ObjectMapper().readValue(responseBodyString, GetTokenResponse.class);
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 6c"));
			token = responseBody.getAccess_token();
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 7c "+token));
			expires = responseBody.getExpires_in();
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 8c "+expires));
			if (token == null || expires == null) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"unexpected reply: "));
				token = null;
				expires = null;
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"OAUT2 token set to null"));
				return token;
			}
			
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 9c"));
			String out = "token di accesso apiman[" + counter + "," + hashCount + "] " + Util.maskForLog(token)
					+ " expires in " + expires;

			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,out));
		} catch (IOException e) {
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 10c"));
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,
					"cannot deserialize token response: [" + responseBodyString + "]"));
			token = null;
			expires = null;
			LOGGER.warn(getClassFunctionDebugString(CLASSNAME, methodName, "OAUT2 token set to null"));
			return token;
		}
		LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 11c"));
		return token;
	}

}
