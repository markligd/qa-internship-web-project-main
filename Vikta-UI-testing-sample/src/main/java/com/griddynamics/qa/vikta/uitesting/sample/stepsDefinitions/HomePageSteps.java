package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.HomePage;
import com.griddynamics.qa.vikta.uitesting.sample.utils.ApiMethods;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;

/**
 * Home page related step Definitions
 */
public class HomePageSteps extends BaseSteps {

  public HomePageSteps(WebDriver driver) {
    super(driver);
  }

  @Step
  public void openHomePage() {
    getDriver().get(getData().baseUrl());
  }

  @Step
  public void clickSearchButton() {
    page().clickSearchButton();
  }

  @Step
  public String getFirstProductTitle() {
    String firstTitle = ApiMethods.getListOfTitlesOfAvailableItems().get(0);
    page().typeInTermToSearchBar(firstTitle);
    return firstTitle;
  }

  @Step
  public String getSecondProductTitle() {
    String secondTitle = ApiMethods.getListOfTitlesOfAvailableItems().get(1);
    page().typeInTermToSearchBar(secondTitle);
    return secondTitle;
  }

  @Step
  public String getFifthProductTitle() {
    String fifthTitle = ApiMethods.getListOfTitlesOfAvailableItems().get(4);
    page().typeInTermToSearchBar(fifthTitle);
    return fifthTitle;
  }

  @Step
  public void checkIfSearchedProductTitleIsInResultsPage(String productTitle) {
    List<String> listOfProductTitles = page()
      .getProductsTitlesFromCurrentPage()
      .stream()
      .map(webElement -> webElement.getText())
      .collect(Collectors.toList());
    assertThat(listOfProductTitles).contains(productTitle);
  }

  private HomePage page() {
    return getPage(HomePage.class);
  }
}
