package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

/**
 * Login functionality related steps.
 */
@Component
public class LoginSteps extends BaseSteps {

  @Step
  public void openLoginPage() {
    getWebDriver().get(getData().loginPageUrl());
  }

  @Step
  public void login(String username, String password) {
    loginPage.login(username, password);
  }

  @Step
  public void loginAsRegularUser() {
    loginPage.login(getData().userName(), getData().userPassword());
  }

  @Step
  public void loginAsAdmin() {
    loginPage.login(getData().adminName(), getData().adminPassword());
  }

  @Step
  public void checkIfCurrentPageIsHomePageForCreatedUser(String userName) {
    verifyCurrentPageIsHomePageForTheUser(userName);
  }

  @Step
  public void verifyCurrentPageIsHomePageForTheRegularUser() {
    verifyCurrentPageIsHomePageForTheUser(getData().userName());
  }

  @Step
  public void verifyCurrentPageIsHomePageForTheAdmin() {
    verifyCurrentPageIsHomePageForTheUser(getData().adminName());
  }

  @Step
  public void verifyErrorMessage(String text) {
    getWait().until(ExpectedConditions.visibilityOf(loginPage.getErrorWebElement()));
    Assertions
        .assertThat(loginPage.getErrorMessage().trim())
        .as("Error message was nor shown or had unexpected content.")
        .contains(text);
  }
}
