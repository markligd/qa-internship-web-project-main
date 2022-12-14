package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import com.codeborne.selenide.WebDriverRunner;
import com.griddynamics.qa.vikta.uitesting.sample.config.DataProvider;
import com.griddynamics.qa.vikta.uitesting.sample.config.TestDataAndProperties;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.BasePage;
import java.util.Objects;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base class to contain common auxiliary methods for step definitions.
 */
abstract class BaseSteps {

  private WebDriver driver;
  private WebDriverWait wait;

  BaseSteps(WebDriver driver) {
    this.driver = driver;
  }

  WebDriver getDriver() {
    return this.driver;
  }

  WebDriverWait getWait() {
    if (Objects.isNull(this.wait)) {
      this.wait = new WebDriverWait(getDriver(), getData().waitTimeout());
    }

    return wait;
  }

  TestDataAndProperties getData() {
    return DataProvider.get();
  }

  <P> P getPage(Class<P> pageClass) {
    return PageFactory.initElements(getDriver(), pageClass);
  }

  void verifyCurrentPageIsHomePageForTheUser(String username) {
    BasePage currentPage = getPage(BasePage.class);
    getWait().until(ExpectedConditions.visibilityOf(currentPage.getLoggedInName()));

    assertCurrentPageUrl(getData().baseUrl(), "Home page was expected to be the current one.");

    Assertions
      .assertThat(currentPage.getCurrentUserName())
      .as("Unexpected current user's name displayed. Expected: %s", username)
      .contains(username);
    //TODO: Assert displayed role as well.
  }

  void assertCurrentPageUrl(String expectedUrl, String messageOnFail) {
    Assertions.assertThat(getDriver().getCurrentUrl()).as(messageOnFail).contains(expectedUrl);
  }
}
