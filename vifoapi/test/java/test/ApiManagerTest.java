/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;


public class ApiManagerTest {

    private static final String apiManagerUrl = "https://www.example.com";
    private static final String tokenEndPoint = "/token";
    private static final String indexEndPoint = "/documentale/indexcsiexp-exp03/v1";
    private static final String modelsEndpoint = "/tenants/vifo/models";

    private static final String tokenUrl = apiManagerUrl + tokenEndPoint;
    private static final String indexUrl = apiManagerUrl + indexEndPoint + modelsEndpoint;

    public static void main(String[] args) throws IOException {
        try {
            // istanzia apiManager
            /*
            ApiManagerServiceHelper apiManager = new ApiManagerServiceHelper(tokenUrl);
            OauthHelper oauthHelper = apiManager.getOauthHelper();
            String token = oauthHelper.getToken();
            System.out.println("Token : " + token);
            */

            //chiamata servizio index
            String token="b1a8d917-c737-3694-9dcb-b947bcb946dd";
            String urlPing = "https://www.example.com";
            Client client = ClientBuilder.newBuilder().build();
            WebTarget target = client.target(urlPing);
            //System.out.println(indexUrl);
            Response resp = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).get();
            if (resp.getStatus() >= 400) {
                //GenericType<Error> errGenericType = new GenericType<Error>() {};
                //Error err = resp.readEntity(errGenericType);
                System.out.println("SERVER EXCEPTION : " + resp.toString());
                //throw new GenericException(err.getMessage());
            } else {
                System.out.println(resp.getHeaders());
            }
            //GenericType<ConfigurationResponse> configurationResponseType = new GenericType<ConfigurationResponse>() {            };
            //ConfigurationResponse response = resp.readEntity(configurationResponseType);
            //if (response != null) {
                // fai qualcosa
            //}


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}