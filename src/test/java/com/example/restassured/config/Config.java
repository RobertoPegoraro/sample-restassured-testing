package com.example.restassured.config;


import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private Dotenv dotenv;

    public Config() {
        dotenv = Dotenv.load();
    }

    public String get(String key) {
        return dotenv.get(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(dotenv.get(key));
    }
}
