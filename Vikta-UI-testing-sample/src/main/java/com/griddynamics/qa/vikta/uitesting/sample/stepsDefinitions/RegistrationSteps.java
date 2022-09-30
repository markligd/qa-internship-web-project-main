package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import com.griddynamics.qa.vikta.uitesting.sample.auxiliary.DriverManager;
import com.griddynamics.qa.vikta.uitesting.sample.config.TestDataConfiguration;
import com.griddynamics.qa.vikta.uitesting.sample.config.TestSetupConfiguration;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.RegistrationPage;
import com.griddynamics.qa.vikta.uitesting.sample.utils.StringHelper;
import io.qameta.allure.Step;
import org.apache.commons.lang.NotImplementedException;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Registration functionality related steps.
 */
public class RegistrationSteps {

  private static String SUCCESSFUL_REGISTRATION_MESSAGE_PREFIX =
    "User has been registered successfully: ";
  private static String FAILED_REGISTRATION_MESSAGE_PREFIX =
    "There is already a user registered with the loginname provided";

  @Autowired
  private RegistrationPage registrationPage;

  @Autowired
  private DriverManager driverManager;

  @Autowired
  private TestSetupConfiguration testSetupConfiguration;

  @Autowired
  private TestDataConfiguration testDataConfiguration;

  @Step
  public void openRegistrationPage() {
    driverManager.get().get(testSetupConfiguration.getRegistrationPageUrl());
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
      testSetupConfiguration.getRegistrationPageUrl(),
      "Registration page was expected to be the current one."
    );
  }

  @Step
  public String typeInExistingUsername() {
    String existingLoginName = testDataConfiguration.getUserName();
    registrationPage.typeInLoginname(existingLoginName);
    return existingLoginName;
  }

  @Step
  public void verifySuccessfulRegistrationMessageIsDisplayed() {
    Assertions
      .assertThat(registrationPage.getMessageText().trim())
      .as("Successful registration message was nor shown or had unexpected content.")
      .startsWith(SUCCESSFUL_REGISTRATION_MESSAGE_PREFIX);
  }

  @Step
  public void checkFailedRegistrationMessageIsDisplayed() {
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

  private void assertCurrentPageUrl(String registrationPageUrl, String s) {
    throw new NotImplementedException();
  }
}
