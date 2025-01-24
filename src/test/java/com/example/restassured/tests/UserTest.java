package com.example.restassured.tests;

import com.example.restassured.factory.UserRequestFactory;
import com.example.restassured.model.request.UsersRequest;
import com.example.restassured.model.response.ErrorResponse;
import com.example.restassured.model.response.UsersResponse;
import com.example.restassured.request.RequestUtils;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class UserTest {

    private RequestUtils requestUtils;

    @BeforeClass
    public void setup() {
        requestUtils = new RequestUtils();
    }

    @Test
    public void shouldRetrieveListOfUsers() {

        List<UsersResponse> usersResponses = requestUtils.getList(new UsersRequest(), UsersResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            //Check if the list size is not empty
            softly.assertThat(usersResponses.isEmpty()).isEqualTo(false);

            //Retrieve the first user and check if all the fields aren't empty
            UsersResponse usersResponse = usersResponses.get(0);
            softly.assertThat(usersResponse.getId()).isGreaterThan(0);
            softly.assertThat(usersResponse.getEmail()).isNotEmpty();
            softly.assertThat(usersResponse.getName()).isNotEmpty();
            softly.assertThat(usersResponse.getGender()).isNotEmpty();
            softly.assertThat(usersResponse.getStatus()).isNotEmpty();
        });
    }

    @Test
    public void shouldSaveNewUser() {
        UsersRequest userRequest = UserRequestFactory.generateNewUserData();

        UsersResponse response = requestUtils.post(userRequest, UsersResponse.class);

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
        UsersRequest userRequest = UserRequestFactory.generateNewUserData();
        //First request
        UsersResponse successResponse = requestUtils.post(userRequest, UsersResponse.class);
        //Second duplicated request
        List<ErrorResponse> failedResponse = (List<ErrorResponse>) requestUtils.post(userRequest, ErrorResponse.class);
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
