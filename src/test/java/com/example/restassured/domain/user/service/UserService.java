package com.example.restassured.domain.user.service;

import com.example.restassured.core.http.client.HttpClient;
import com.example.restassured.core.http.client.HttpClientFactory;
import com.example.restassured.domain.user.model.UserRequest;
import com.example.restassured.core.http.model.ApiResponseWrapper;
import com.example.restassured.domain.user.model.UserResponse;

import java.util.List;

public class UserService {

    private static final String USERS = UserEndpoint.USER;

    private final HttpClient httpClient;

    public UserService() {

        httpClient = HttpClientFactory.getClient();
    }

    public ApiResponseWrapper<List<UserResponse>> getUsers() {

        return httpClient.getList(USERS, UserResponse.class);
    }

    public ApiResponseWrapper<UserResponse> createUser(UserRequest request) {

        return httpClient.post(USERS, request, UserResponse.class);
    }
}
