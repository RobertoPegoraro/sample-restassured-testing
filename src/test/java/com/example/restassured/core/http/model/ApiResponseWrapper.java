package com.example.restassured.core.http.model;

import lombok.Getter;

import java.util.List;

@Getter
public class ApiResponseWrapper<T> {

    private T data;
    private List<ErrorResponse> errors;

    public static <T> ApiResponseWrapper<T> success(T data) {
        ApiResponseWrapper<T> r = new ApiResponseWrapper<>();
        r.data = data;
        return r;
    }

    public static <T> ApiResponseWrapper<T> error(List<ErrorResponse> errors) {
        ApiResponseWrapper<T> r = new ApiResponseWrapper<>();
        r.errors = errors;
        return r;
    }
}
