package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import com.griddynamics.qa.vikta.uitesting.sample.utils.StringHelper;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

/**
 * Registration functionality related steps.
 */
@Component
public class RegistrationSteps extends BaseSteps {

  private static String SUCCESSFUL_REGISTRATION_MESSAGE_PREFIX =
    "User has been registered successfully: ";

  private static String FAILED_REGISTRATION_MESSAGE_PREFIX =
    "There is already a user registered with the loginname provided";

  @Step
  public void openRegistrationPage() {
    getWebDriver().get(getData().registrationPageUrl());
  }

  @Step
  public String fillInRegistrationFormWithLoginName() {
    String randomLoginName = StringHelper.generateRandomLoginName();
    registrationPage.typeInLoginname(randomLoginName);
    return randomLoginName;
  }

  @Step
  public void fillInRegistrationFormWithSurname() {
    String randomSurname = StringHelper.generateRandomSurname();
    registrationPage.typeInSurname(randomSurname);
  }

  @Step
  public void fillInRegistrationFormWithFirstName() {
    String randomFirstName = StringHelper.generateRandomFirstNameOrPatronim();
    registrationPage.typeInFirstname(randomFirstName);
  }

  @Step
  public void fillInRegistrationFormWithPatronim() {
    String randomPatronim = StringHelper.generateRandomFirstNameOrPatronim();
    registrationPage.typeInPatronim(randomPatronim);
  }

  @Step
  public void fillInRegistrationFormWithEmail() {
    String randomEmail = StringHelper.generateRandomEmail();
    registrationPage.typeInEmail(randomEmail);
  }

  @Step
  public String fillInRegistrationFormWithPassword() {
    String randomPassword = StringHelper.generateRandomPassword();
    registrationPage.typeInPassword(randomPassword);
    return randomPassword;
  }

  @Step
  public void clickRegisterUserButton() {
    registrationPage.clickRegisterUserButton();
  }

  @Step
  public void verifyCurrentPageIsRegistration() {
    assertCurrentPageUrl(
      getData().registrationPageUrl(),
      "Registration page was expected to be the current one."
    );
  }

  @Step
  public String typeInExistingUsername() {
    String existingLoginName = getData().userName();
    registrationPage.typeInLoginname(existingLoginName);
    return existingLoginName;
  }

  @Step
  public void verifySuccessfulRegistrationMessageIsDisplayed() {
    getWait().until(ExpectedConditions.visibilityOf(registrationPage.getMessageWebElement()));
    Assertions
      .assertThat(registrationPage.getMessageText().trim())
      .as("Successful registration message was nor shown or had unexpected content.")
      .startsWith(SUCCESSFUL_REGISTRATION_MESSAGE_PREFIX);
  }

  @Step
  public void checkFailedRegistrationMessageIsDisplayed() {
    getWait()
      .until(ExpectedConditions.visibilityOf(registrationPage.getUserAlreadyExistsMessageWebElement()));
    Assertions
      .assertThat(registrationPage.getUserAlreadyExistsMessageText().trim())
      .as("Failed registration message was nor shown or had unexpected content.")
      .startsWith(FAILED_REGISTRATION_MESSAGE_PREFIX);
  }

  @Step
  public void verifySuccessfulRegistrationMessageContainsNewUsername(String loginnameUsed) {
    Assertions
      .assertThat(registrationPage.getMessageText().trim())
      .as("Successful registration message was expected to contain the new username used.")
      .contains(loginnameUsed);
  }
}
