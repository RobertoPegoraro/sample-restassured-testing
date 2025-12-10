package com.example.restassured.tests.user;

import com.example.restassured.domain.user.factory.UserFactory;
import com.example.restassured.domain.user.model.UserRequest;
import com.example.restassured.core.http.model.ErrorResponse;
import com.example.restassured.domain.user.model.UserResponse;
import com.example.restassured.domain.user.service.UserService;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

public class CreateUserTest {

    UserService userService = new UserService();

    @Test
    public void shouldSaveNewUser() {
        UserRequest userRequest = UserFactory.generateNewUserData();

        UserResponse response = userService.createUser(userRequest).getData();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getId()).isGreaterThan(0);
            softly.assertThat(response.getEmail()).isEqualTo(userRequest.getEmail());
            softly.assertThat(response.getName()).isEqualTo(userRequest.getName());
            softly.assertThat(response.getGender()).isEqualTo(userRequest.getGender().toLowerCase());
            softly.assertThat(response.getStatus()).isEqualTo(userRequest.getStatus());
        });
    }

    @Test
    public void shouldReturnErrorDuplicatedUser() {
        UserRequest userRequest = UserFactory.generateNewUserData();

        //First request
        userService.createUser(userRequest);

        //Second duplicated request
        List<ErrorResponse> failedResponse = userService.createUser(userRequest).getErrors();
        SoftAssertions.assertSoftly(softly -> {

            //Check the errors list size
            softly.assertThat(failedResponse.size()).isEqualTo(1);

            //Check the error message
            ErrorResponse errorResponse = failedResponse.get(0);
            softly.assertThat(errorResponse.getField()).isEqualTo("email");
            softly.assertThat(errorResponse.getMessage()).isEqualTo("has already been taken");
        });
    }
}
