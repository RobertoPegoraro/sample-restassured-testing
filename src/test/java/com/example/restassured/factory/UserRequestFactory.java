package com.example.restassured.factory;

import com.example.restassured.enums.UserStatus;
import com.example.restassured.model.request.UsersRequest;
import com.example.restassured.utils.FakerUtils;

public class UserRequestFactory {

    public static UsersRequest generateNewUserData() {
        return UsersRequest.builder()
                .name(FakerUtils.generateRandomName())
                .email(FakerUtils.generateRandomEmail())
                .gender(FakerUtils.generateRandomGender())
                .status(UserStatus.ACTIVE.getValue())
                .build();
    }
}
