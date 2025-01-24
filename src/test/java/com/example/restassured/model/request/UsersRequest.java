package com.example.restassured.model.request;

import com.example.restassured.request.Url;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequest implements Url {

    private String name;

    private String email;

    private String gender;

    private String status;

    @Override
    public String endpoint() {
        return "/public/v2/users";
    }
}
