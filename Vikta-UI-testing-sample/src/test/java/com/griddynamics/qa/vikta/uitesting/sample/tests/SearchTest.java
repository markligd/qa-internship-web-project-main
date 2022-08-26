package com.griddynamics.qa.vikta.uitesting.sample.tests;

import static io.restassured.RestAssured.given;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.HomePage;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.HomePageSteps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

  @Test
  public void findFirstImageByTitle() {
    homePageSteps.openHomePage();

    String title = homePageSteps.getFirstProductTitle();

    homePageSteps.clickSearchButton();

    homePageSteps.checkIfSearchedProductTitleIsInResultsPage(title);
  }

  @Test
  public void findSecondImageByTitle() {
    homePageSteps.openHomePage();

    String title = homePageSteps.getSecondProductTitle();

    homePageSteps.clickSearchButton();

    homePageSteps.checkIfSearchedProductTitleIsInResultsPage(title);
  }

  @Test
  public void findFifthImageByTitle() {
    homePageSteps.openHomePage();

    String title = homePageSteps.getFifthProductTitle();

    homePageSteps.clickSearchButton();

    homePageSteps.checkIfSearchedProductTitleIsInResultsPage(title);
  }
}
