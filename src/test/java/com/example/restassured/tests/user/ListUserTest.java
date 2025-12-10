package com.example.restassured.tests.user;

import com.example.restassured.domain.user.model.UserResponse;
import com.example.restassured.domain.user.service.UserService;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

public class ListUserTest {

    UserService userService = new UserService();

    @Test
    public void shouldRetrieveListOfUsers() {

        List<UserResponse> usersResponses = userService.getUsers().getData();

        SoftAssertions.assertSoftly(softly -> {
            //Check if the list size is not empty
            softly.assertThat(usersResponses.isEmpty()).isEqualTo(false);

            //Retrieve the first user and check if all the fields aren't empty
            UserResponse usersResponse = usersResponses.get(0);
            softly.assertThat(usersResponse.getId()).isGreaterThan(0);
            softly.assertThat(usersResponse.getEmail()).isNotEmpty();
            softly.assertThat(usersResponse.getName()).isNotEmpty();
            softly.assertThat(usersResponse.getGender()).isNotEmpty();
            softly.assertThat(usersResponse.getStatus()).isNotEmpty();
        });
    }
}
