package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import com.griddynamics.qa.vikta.uitesting.sample.auxiliary.DriverManager;
import com.griddynamics.qa.vikta.uitesting.sample.config.TestDataConfiguration;
import com.griddynamics.qa.vikta.uitesting.sample.config.TestSetupConfiguration;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.BasePage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.HomePage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.LoginPage;
import io.qameta.allure.Step;
import org.apache.commons.lang.NotImplementedException;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Login functionality related steps.
 */
public class LoginSteps {

  @Autowired
  private LoginPage loginPage;

  @Autowired
  private DriverManager driverManager;

  @Autowired
  private TestSetupConfiguration testSetupConfiguration;

  @Autowired
  private TestDataConfiguration testDataConfiguration;

  @Step
  public void openLoginPage() {
    driverManager.get().get(testSetupConfiguration.getLoginPageUrl());
  }

  @Step
  public void login(String username, String password) {
    loginPage.login(username, password);
  }

  @Step
  public void loginAsRegularUser() {
    loginPage.login(testDataConfiguration.getUserName(), testDataConfiguration.getUserPassword());
  }

  @Step
  public void loginAsAdmin() {
    loginPage.login(testDataConfiguration.getAdminName(), testDataConfiguration.getAdminPassword());
  }

  @Step
  public void checkIfCurrentPageIsHomePageForCreatedUser(String userName) {
    verifyCurrentPageIsHomePageForTheUser(userName);
  }

  @Step
  public void verifyCurrentPageIsHomePageForTheRegularUser() {
    verifyCurrentPageIsHomePageForTheUser(testDataConfiguration.getUserName());
  }

  @Step
  public void verifyCurrentPageIsHomePageForTheAdmin() {
    verifyCurrentPageIsHomePageForTheUser(testDataConfiguration.getAdminName());
  }

  @Step
  public void verifyErrorMessage(String text) {
    Assertions
      .assertThat(loginPage.getErrorMessage().trim())
      .as("Error message was nor shown or had unexpected content.")
      .contains(text);
  }

  private void verifyCurrentPageIsHomePageForTheUser(String userName) {
    throw new NotImplementedException();
  }
}
