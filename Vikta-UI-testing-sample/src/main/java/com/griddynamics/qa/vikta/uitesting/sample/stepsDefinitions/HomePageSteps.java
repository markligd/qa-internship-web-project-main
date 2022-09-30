package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.griddynamics.qa.vikta.uitesting.sample.auxiliary.DriverManager;
import com.griddynamics.qa.vikta.uitesting.sample.config.TestSetupConfiguration;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.HomePage;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Home page related step Definitions
 */
public class HomePageSteps {

  @Autowired
  private HomePage homePage;

  @Autowired
  private DriverManager driverManager;

  @Autowired
  private TestSetupConfiguration testSetupConfiguration;

  @Step
  public void openHomePage() {
    driverManager.get().get(testSetupConfiguration.getBaseUrl());
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
