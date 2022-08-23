package com.griddynamics.qa.vikta.uitesting.sample.utils;

import java.util.UUID;

public class FillInStringGenerator {

  public static String generateRandomString(int maxLength) {
    String candidate = UUID.randomUUID().toString().replaceAll("\\d", "A");
    if (candidate.length() >= maxLength) {
      return candidate.substring(0, maxLength);
    } else {
      return candidate;
    }
  }

  public static String generateRandomString() {
    return generateRandomString(16); //Remember!!! Magic numbers are bad, bad, bad practice!
  }
}
