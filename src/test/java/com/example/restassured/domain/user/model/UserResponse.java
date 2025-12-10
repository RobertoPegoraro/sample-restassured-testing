package com.example.restassured.domain.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private String gender;

    private String status;
}
