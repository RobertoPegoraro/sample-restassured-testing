package com.example.restassured.core.http.client;

import com.example.restassured.core.config.ConfigReader;
import com.example.restassured.core.http.model.ApiResponseWrapper;
import com.example.restassured.core.http.header.HeaderUtils;
import com.example.restassured.core.http.parser.ResponseParser;
import com.google.gson.Gson;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

import java.util.List;

public class RestAssuredHttpClient implements HttpClient {

    private static final Logger log = LoggerFactory.getLogger(RestAssuredHttpClient.class);

    private final String baseUrl;
    private final Gson gson;

    public RestAssuredHttpClient() {
        this.baseUrl = ConfigReader.get("BASE_URL");
        this.gson = new Gson();
    }

    @Override
    public <T> ApiResponseWrapper<List<T>> getList(String endpoint, Class<T> clazz) {

        final Response response = given()
                .headers(HeaderUtils.getDefaulHeaders())
                .get(baseUrl + endpoint);

        logResponse(response);

        if (response.getStatusCode() >= 400) {
            return ApiResponseWrapper.error(ResponseParser.convertErrorList(response));
        }

        List<T> data = response.jsonPath().getList(".", clazz);
        return ApiResponseWrapper.success(data);
    }

    @Override
    public <T> ApiResponseWrapper<T> post(String endpoint, Object requestObject, Class<T> responseObject) {

        final String request = gson.toJson(requestObject);

        log.info("Request: {}", request);

        final Response response = given()
                .headers(HeaderUtils.getDefaulHeaders())
                .body(request)
                .post(baseUrl + endpoint);

        logResponse(response);

        if (response.getStatusCode() >= 400) {
            return ApiResponseWrapper.error(ResponseParser.convertErrorList(response));
        }

        return ApiResponseWrapper.success(response.getBody().as(responseObject));
    }

    private void logResponse(Response response) {
        log.info("Response HTTP Code: {}", response.getStatusCode());
        log.info("Response Body: {}", response.getBody().asString());
    }
}
