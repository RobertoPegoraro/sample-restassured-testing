package com.example.restassured.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    private static final Faker faker = Faker.instance();

    public static String generateRandomName() {
        return faker.name().fullName();
    }

    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateRandomGender() {
        return faker.demographic().sex();
    }
}
