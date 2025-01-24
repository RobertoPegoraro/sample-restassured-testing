package com.example.restassured.request;

import com.example.restassured.config.Config;
import com.example.restassured.config.ConfigKeys;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class HeaderUtils {

    protected static Headers getDefaulHeaders() {
        Config config = new Config();
        return new Headers(
                new Header("Accept", "*/*"),
                new Header("Content-Type", "application/json"),
                new Header("Authorization", config.get(ConfigKeys.TOKEN)));

    }
}
