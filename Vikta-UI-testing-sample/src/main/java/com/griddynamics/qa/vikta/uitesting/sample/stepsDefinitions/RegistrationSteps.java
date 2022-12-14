package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.RegistrationPage;
import com.griddynamics.qa.vikta.uitesting.sample.utils.StringHelper;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Registration functionality related steps.
 */
public class RegistrationSteps extends BaseSteps {

  private static String SUCCESSFUL_REGISTRATION_MESSAGE_PREFIX =
    "User has been registered successfully: ";

  private static String FAILED_REGISTRATION_MESSAGE_PREFIX =
    "There is already a user registered with the loginname provided";

  public RegistrationSteps(WebDriver driver) {
    super(driver);
  }

  @Step
  public void openRegistrationPage() {
    getDriver().get(getData().registrationPageUrl());
  }

  @Step
  public String fillInRegistrationFormWithLoginName() {
    String randomLoginName = StringHelper.generateRandomLoginName();
    page().typeInLoginname(randomLoginName);
    return randomLoginName;
  }

  @Step
  public void fillInRegistrationFormWithSurname() {
    String randomSurname = StringHelper.generateRandomSurname();
    page().typeInSurname(randomSurname);
  }

  @Step
  public void fillInRegistrationFormWithFirstName() {
    String randomFirstName = StringHelper.generateRandomFirstNameOrPatronim();
    page().typeInFirstname(randomFirstName);
  }

  @Step
  public void fillInRegistrationFormWithPatronim() {
    String randomPatronim = StringHelper.generateRandomFirstNameOrPatronim();
    page().typeInPatronim(randomPatronim);
  }

  @Step
  public void fillInRegistrationFormWithEmail() {
    String randomEmail = StringHelper.generateRandomEmail();
    page().typeInEmail(randomEmail);
  }

  @Step
  public String fillInRegistrationFormWithPassword() {
    String randomPassword = StringHelper.generateRandomPassword();
    page().typeInPassword(randomPassword);
    return randomPassword;
  }

  @Step
  public void clickRegisterUserButton() {
    page().clickRegisterUserButton();
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
    page().typeInLoginname(existingLoginName);
    return existingLoginName;
  }

  @Step
  public void verifySuccessfulRegistrationMessageIsDisplayed() {
    getWait().until(ExpectedConditions.visibilityOf(page().getMessageWebElement()));
    Assertions
      .assertThat(page().getMessageText().trim())
      .as("Successful registration message was nor shown or had unexpected content.")
      .startsWith(SUCCESSFUL_REGISTRATION_MESSAGE_PREFIX);
  }

  @Step
  public void checkFailedRegistrationMessageIsDisplayed() {
    getWait()
      .until(ExpectedConditions.visibilityOf(page().getUserAlreadyExistsMessageWebElement()));
    Assertions
      .assertThat(page().getUserAlreadyExistsMessageText().trim())
      .as("Failed registration message was nor shown or had unexpected content.")
      .startsWith(FAILED_REGISTRATION_MESSAGE_PREFIX);
  }

  @Step
  public void verifySuccessfulRegistrationMessageContainsNewUsername(String loginnameUsed) {
    Assertions
      .assertThat(page().getMessageText().trim())
      .as("Successful registration message was expected to contain the new username used.")
      .contains(loginnameUsed);
  }

  //TODO: Think about generics etc instead of this.
  private RegistrationPage page() {
    return getPage(RegistrationPage.class);
  }
}
