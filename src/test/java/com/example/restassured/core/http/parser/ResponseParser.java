package com.example.restassured.core.http.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.example.restassured.core.http.model.ErrorResponse;
import io.restassured.response.Response;

import java.util.ArrayList;

public class ResponseParser {

    public static <T> T convertErrorList(Response response) {
        final ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, ErrorResponse.class);
        try {
            return objectMapper.readValue(response.body().print(), listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
