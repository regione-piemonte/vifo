/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

import static it.csi.vifo.vifoapi.util.builder.ToStringBuilder.objectToString;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.dto.ApiManagerDto;
import it.csi.vifo.vifoapi.dto.ErrorMessageDTO;
import it.csi.vifo.vifoapi.dto.ErrorMessageWrapperDTO;
import it.csi.vifo.vifoapi.dto.RespDocumento;
import it.csi.vifo.vifoapi.dto.RespPratica;
import it.csi.vifo.vifoapi.dto.ResponseDTO;
import it.csi.vifo.vifoapi.util.oauth2.InternalOauthHelper;

public class CallRestJsonUtils extends BaseApiServiceImpl {

	public CallRestJsonUtils(String urlToken) {
		super();
		this.urlToken = urlToken;
	}

	public static final String AUTHORIZATION_HEADER_PARAM = "Authorization";
	public static final String AUTHORIZATION_HEADER_PARAM_VALUE_PREFIX = "Bearer ";
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	private final String urlToken;
	
	private final boolean isSkeepApiManager = false;

	public ResponseDTO sendPost(String wsUri, ApiManagerDto apiManager, String jsonInputString) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				
	    LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
		StringBuilder response = new StringBuilder();
		URL url = new URL(wsUri);
		ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RespPratica respPratica = null;
		ErrorMessageDTO error = null;
		ResponseDTO respDTO = new ResponseDTO();

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(Constants.POST_TIME_OUT); // millisec
		connection.setReadTimeout(Constants.POST_TIME_OUT);

		if(isSkeepApiManager) {
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "----- 1a basicAuth"));
			String userpass = "foreste" + ":" + "foreste";
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
			 connection.setRequestProperty ("Authorization", basicAuth);
		}else {
		
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "----- 1a token "));
		
		    LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, objectToString(apiManager)));
		    LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "urlToken  --> "+urlToken));

			InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
					apiManager.getConsumerKey(), apiManager.getConsumerSecret());
			
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "----- 2a"));
			try {
				String token = oauthHelper.getToken();
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "----- 3a, token: " + token));
				connection.setRequestProperty(AUTHORIZATION_HEADER_PARAM, AUTHORIZATION_HEADER_PARAM_VALUE_PREFIX + token);
			} catch (Throwable ex) {
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "----- 4a"));
				ex.printStackTrace();
			}
		
		}
		
		LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "----- 5a"));
		try (OutputStream os = connection.getOutputStream()) {
			
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
			os.flush();
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "----- 6a"));
			InputStream in = null;
			int responseCode = connection.getResponseCode();
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"responseCode: " + responseCode + " - " + connection.getResponseMessage()));
			if (responseCode == HttpStatus.SC_OK || responseCode == HttpStatus.SC_CREATED) {
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "----- 7a"));
				in = connection.getInputStream();
				String resp = IOUtils.toString(in, StandardCharsets.UTF_8.name());
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"RespPratica json: " + resp));
				respPratica = om.readValue(resp, RespPratica.class);
				respDTO.setIdPratica(respPratica.getIdPratica());				
			} else {
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "----- 8a"));
				in = connection.getErrorStream();
				if (in != null) {
					in = connection.getErrorStream();
					String errorSteam = IOUtils.toString(in, StandardCharsets.UTF_8.name());
					LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "ErrorSteam: " + errorSteam));
			
					JsonNode fullErrorNode = om.readTree(errorSteam);
					JsonNode errorNode = fullErrorNode.get("fault");
					if(errorNode != null) {
						error = (om.convertValue(errorNode, ErrorMessageWrapperDTO.class)).getErrorMessageDTO();
					}else {
						error = om.readValue(errorSteam, ErrorMessageDTO.class);
					}
					respDTO.setErrore(error);
					LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, 
							error.getCode() + " - " + error.getStatus() + " - " + error.getTitle()));
				} else {
					throw new Exception("Impossibile leggere lo stream di output del servizio!!!");
				}
			}
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,response.toString()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName)); 
		}

		return respDTO;

	}

	public ResponseDTO sendFilePost(String wsUri, ApiManagerDto apiManager, byte[] fileByte, String nomeFile) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
		StringBuilder response = new StringBuilder();
		RespPratica respPratica = null;
		ErrorMessageDTO error = null;
		ResponseDTO respDTO = new ResponseDTO();
		URL url = new URL(wsUri);
		DataOutputStream dos = null;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		
		conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
		conn.setConnectTimeout(Constants.POST_TIME_OUT); // millisec
		conn.setReadTimeout(Constants.POST_TIME_OUT);
		LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,("----- 1a")));
		InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
        apiManager.getConsumerKey(), apiManager.getConsumerSecret());
		LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 2a"));
    try {
      String token = oauthHelper.getToken();
      LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 3a"));
      conn.setRequestProperty(AUTHORIZATION_HEADER_PARAM, AUTHORIZATION_HEADER_PARAM_VALUE_PREFIX + token);
    } catch (Throwable ex) {
    	LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 4a"));
      ex.printStackTrace();
    }
		
		

		try {
			// Apro lo streaming verso la servlet
			dos = new DataOutputStream(conn.getOutputStream());

			// Scrivo la prima riga
			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes(
					"Content-Disposition: form-data; name=\"file\";" + " filename=\"" + nomeFile + "\"" + lineEnd);
			String mimeType = VifoUtils.FILE.getMimeTypeFromFileName(nomeFile);
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"mimeType: "+mimeType));
	    
			dos.writeBytes("Content-Type: "+mimeType + lineEnd);
			dos.writeBytes(lineEnd);

			int maxBufferSize = 1 * 1024 * 1024;
			InputStream targetStream = new ByteArrayInputStream(fileByte);

			
			bytesAvailable = targetStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			bytesRead = targetStream.read(buffer, 0, bufferSize);
			while (bytesRead > 0) {

				dos.write(buffer, 0, bufferSize);
				bytesAvailable = targetStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = targetStream.read(buffer, 0, bufferSize);

			}
			
			targetStream.close();
			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			InputStream in = null;
			int responseCode = conn.getResponseCode();
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"responseCode: " + responseCode + " - " + conn.getResponseMessage()));
			
			if (responseCode == HttpStatus.SC_OK || responseCode == HttpStatus.SC_CREATED) {

				in = conn.getInputStream();
				respPratica = om.readValue(in, RespPratica.class);
				respDTO.setUploadUUID(respPratica.getUploadUUID());
			} else {

				in = conn.getErrorStream();
				if (in != null) {
					in = conn.getErrorStream();
					error = om.readValue(in, ErrorMessageDTO.class);
					respDTO.setErrore(error);
					LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,
							error.getCode() + " - " + error.getStatus() + " - " + error.getTitle()));
				} else {
					throw new Exception("Impossibile leggere lo stream di output del servizio!!!");
				}
			}
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"Server response is: " + response.toString()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

		return respDTO;

	}

	public ResponseDTO sendPostDocumento(String wsUri, ApiManagerDto apiManager, String jsonInputString) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
		
		StringBuilder response = new StringBuilder();
		URL url = new URL(wsUri);
		ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RespDocumento respDocumento = null;
		ErrorMessageDTO error = null;
		ResponseDTO respDTO = new ResponseDTO();

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(Constants.POST_TIME_OUT); // millisec
		connection.setReadTimeout(Constants.POST_TIME_OUT);
		
		LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 1a"));
		InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
        apiManager.getConsumerKey(), apiManager.getConsumerSecret());
		LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 2a"));
		
	    try {
	      String token = oauthHelper.getToken();
	      LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 3a"));
	      connection.setRequestProperty(AUTHORIZATION_HEADER_PARAM, AUTHORIZATION_HEADER_PARAM_VALUE_PREFIX + token);
	    } catch (Throwable ex) {
	    	LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"----- 4a"));
	      ex.printStackTrace();
	    }

		try (OutputStream os = connection.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
			os.flush();

			InputStream in = null;
			int responseCode = connection.getResponseCode();
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"responseCode: " + responseCode + " - " + connection.getResponseMessage()));
			if (responseCode == HttpStatus.SC_OK || responseCode == HttpStatus.SC_CREATED) {
				in = connection.getInputStream();
				respDocumento = om.readValue(in, RespDocumento.class);
				if (respDocumento != null) {
					if (respDocumento.getEsiti() != null && respDocumento.getEsiti().size() > 0) {
						for (int i = 0; i < respDocumento.getEsiti().size(); i++) {
							if (respDocumento.getEsiti().get(i).getEsito() != null
									&& respDocumento.getEsiti().get(i).getEsito().getStatus() != null) {
								if (Integer.parseInt(respDocumento.getEsiti().get(i).getEsito().getStatus()) != HttpStatus.SC_OK
										&& Integer.parseInt(respDocumento.getEsiti().get(i).getEsito().getStatus())
												 != HttpStatus.SC_CREATED) {
									respDTO.setErrore(respDocumento.getEsiti().get(i).getEsito());
									break;
								}
							}
						}
					}
				}		
			} else {
				in = connection.getErrorStream();
				if (in != null) {
					in = connection.getErrorStream();
					error = om.readValue(in, ErrorMessageDTO.class);
					respDTO.setErrore(error);
					LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,
							error.getStatus() + " - " + error.getCode() + " - " + error.getTitle()));
				} else {
					throw new Exception("Impossibile leggere lo stream di output del servizio!!!");
				}
			}
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,response.toString()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}

		return respDTO;

	}

}
