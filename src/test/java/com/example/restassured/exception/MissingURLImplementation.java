package com.example.restassured.exception;

public class MissingURLImplementation extends RuntimeException {

    public MissingURLImplementation() {
        super("The Request Object must implement the Url interface.");
    }
}
