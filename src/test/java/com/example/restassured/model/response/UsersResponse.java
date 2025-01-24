package com.example.restassured.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersResponse {

    private Long id;

    private String name;

    private String email;

    private String gender;

    private String status;
}
