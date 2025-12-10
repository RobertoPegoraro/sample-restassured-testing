package com.example.restassured.domain.user.factory;

import com.example.restassured.domain.user.enums.UserStatus;
import com.example.restassured.domain.user.model.UserRequest;
import com.example.restassured.utils.FakerUtils;

public class UserFactory {

    public static UserRequest generateNewUserData() {
        return UserRequest.builder()
                .name(FakerUtils.generateRandomName())
                .email(FakerUtils.generateRandomEmail())
                .gender(FakerUtils.generateRandomGender())
                .status(UserStatus.ACTIVE.getValue())
                .build();
    }
}
