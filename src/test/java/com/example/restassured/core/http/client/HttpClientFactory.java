package com.example.restassured.core.http.client;

import com.example.restassured.core.config.ConfigReader;

public class HttpClientFactory {

    private static HttpClient instance;

    public static HttpClient getClient() {
        if (instance == null) {
            String type = ConfigReader.get("HTTP_CLIENT");

            switch (type.toLowerCase()) {

                case "restassured":
                default:
                    instance = new RestAssuredHttpClient();
                    break;
                // To be implemented:
                // case "apache":
                //     instance = new ApacheHttpClient();
            }
        }
        return instance;
    }
}
