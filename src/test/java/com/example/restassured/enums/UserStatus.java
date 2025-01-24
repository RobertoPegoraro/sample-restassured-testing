package com.example.restassured.enums;

public enum UserStatus {

    ACTIVE("active"),
    INACTIVE("inactive");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
