package com.example.restassured.core.http.client;

import com.example.restassured.core.http.model.ApiResponseWrapper;

import java.util.List;

public interface HttpClient {

    <T> ApiResponseWrapper<List<T>> getList(String endpoint, Class<T> clazz);

    <T> ApiResponseWrapper<T> post(String endpoint, Object body, Class<T> responseType);
}
