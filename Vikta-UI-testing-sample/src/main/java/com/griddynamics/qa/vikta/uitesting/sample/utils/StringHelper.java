package com.griddynamics.qa.vikta.uitesting.sample.utils;

import com.github.javafaker.Faker;

import java.util.UUID;

public class StringHelper {

    private static final Faker faker = new Faker();

    public static String generateRandomLoginName() {
        return faker.name().username();
    }

    public static String generateRandomSurname() {
        return faker.name().lastName();
    }

    public static String generateRandomFirstNameOrPatronim() {
        return faker.name().firstName();
    }

    public static String generateRandomEmail() {
        return faker.bothify("????##@example.com");
    }

    public static String generateRandomPassword() {
        return faker.bothify("[a-z1-9]{10}");
    }
}
