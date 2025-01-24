package com.example.restassured.request;

import com.example.restassured.config.Config;
import com.example.restassured.config.ConfigKeys;
import com.example.restassured.exception.MissingURLImplementation;
import com.google.gson.Gson;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;

public class RequestUtils {

    private static String baseUrl;

    public RequestUtils(){
        Config config = new Config();
        baseUrl = config.get(ConfigKeys.BASE_URL);
    }

    public <T> List<T> getList(Url url, Class<T> clazz) {

        return given()
                .get(baseUrl + url.endpoint())
                .getBody()
                .jsonPath()
                .getList(".", clazz);
    }

    public <T> T post(Object requestObject, Class<T> responseObject) {
        if (requestObject instanceof Url) {
            Url url = (Url) requestObject;
            String endpoint = url.endpoint();
            final Response response = given()
                    .headers(HeaderUtils.getDefaulHeaders())
                    .body(new Gson().toJson(requestObject))
                    .post(baseUrl + endpoint);

            if (response.getStatusCode() >= 400) {
                return ResponseConverter.convertErrorList(response);
            }
            return response.getBody().as(responseObject);
        } else {
            throw new MissingURLImplementation();
        }
    }
}
