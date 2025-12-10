package com.example.restassured.core.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigReader {

    public static String get(String key) {
        return Dotenv.load().get(key);
    }
}
