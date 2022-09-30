package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

/**
 * Home page related step Definitions
 */
@Component
public class HomePageSteps extends BaseSteps {

  @Step
  public void openHomePage() {
    getWebDriver().get(getData().baseUrl());
  }

  @Step
  public void clickSearchButton() {
    homePage.clickSearchButton();
  }

  @Step
  public void typeItemTitleToSearchBar(String title) {
    homePage.typeInTermToSearchBar(title);
  }

  @Step
  public void checkIfSearchedProductTitleIsInResultsPage(String productTitle) {
    List<String> listOfProductTitles = homePage
      .getProductsTitlesFromCurrentPage()
      .stream()
      .map(WebElement::getText)
      .collect(Collectors.toList());
    assertThat(listOfProductTitles).contains(productTitle);
  }
}
