package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.HomePage;
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
  public void typeItemTitleToSearchBar(String title) {
    page().typeInTermToSearchBar(title);
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
