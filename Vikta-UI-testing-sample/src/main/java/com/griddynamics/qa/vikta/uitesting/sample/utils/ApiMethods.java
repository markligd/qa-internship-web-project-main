package com.griddynamics.qa.vikta.uitesting.sample.utils;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;

public class ApiMethods {

  public static List<String> getListOfTitlesOfAvailableItems() {
    RestAssured.baseURI = "http://localhost:5054/api/v1/imageitem/list";

    Response response = given()
      .and()
      .when()
      .get()
      .then()
      .statusCode(200)
      .and()
      .extract()
      .response();

    List<String> titles = response.jsonPath().getList("title");

    return titles;
  }
}
