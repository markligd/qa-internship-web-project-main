package com.griddynamics.qa.vikta.uitesting.sample.utils;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;

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

  public static String generateRandomStreetName() {
    return faker.address().streetName();
  }

  public static String generateRandomCityName() {
    return faker.address().cityName();
  }

  public static String generateRandomRegion() {
    return faker.address().country();
  }

  public static String generateRandomPostalCode() {
    return faker.address().zipCode();
  }

  public static String generateRandomAdditionalStreetInfo() {
    return faker.address().streetAddressNumber();
  }

  public static String generateRandomAddressNickname() {
    return faker.name().username();
  }

  public static String generateRandomCardNumber() {
    return faker.numerify("################");
  }

  public static String generateRandomCardCode() {
    return faker.numerify("####");
  }

  public static String generateRandomOwner() {
    return faker.name().fullName();
  }

  public static String generateRandomCardExpirationDate() {
    return faker.numerify("####-##-##");
  }

  public static String generateRandomNickname() {
    return faker.name().username();
  }
}
