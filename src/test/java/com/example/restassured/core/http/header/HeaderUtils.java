package com.example.restassured.core.http.header;

import com.example.restassured.core.config.ConfigReader;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class HeaderUtils {

    public static Headers getDefaulHeaders() {
        return new Headers(
                new Header("Accept", "*/*"),
                new Header("Content-Type", "application/json"),
                new Header("Authorization", ConfigReader.get("TOKEN")));
    }
}
