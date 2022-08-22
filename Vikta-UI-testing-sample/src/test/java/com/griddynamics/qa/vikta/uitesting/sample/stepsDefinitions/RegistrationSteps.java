package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.RegistrationPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.RegistrationPage;
import io.qameta.allure.Step;
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

  public enum FieldName {
    LOGINNAME,
    SURNAME,
    FIRSTNAME,
    PATRONIM,
    EMAIL,
    PASSWORD,
  }

  @Step
  public void openRegistrationPage() {
    getDriver().get(getData().registrationPageUrl());
  }

  @Step
  public String typeRandomValueInto(FieldName fieldName) {
    String valueToReturn;
    switch (fieldName) {
      case LOGINNAME:
        valueToReturn = generateRandomString();
        page().typeInLoginname(valueToReturn);
        break;
      case SURNAME:
        valueToReturn = generateRandomString();
        page().typeInSurname(valueToReturn);
        break;
      case FIRSTNAME:
        valueToReturn = generateRandomString();
        page().typeInFirstname(valueToReturn);
        break;
      case PATRONIM:
        valueToReturn = generateRandomString();
        page().typeInPatronim(valueToReturn);
        break;
      case EMAIL:
        valueToReturn = String.format("%s@exampleemail.com", generateRandomString());
        page().typeInEmail(valueToReturn);
        break;
      case PASSWORD:
        valueToReturn = generateRandomString();
        page().typeInPassword(valueToReturn);
        break;
      default:
        throw new IllegalArgumentException(
          "Unsupported Registration page field name: " + fieldName
        );
    }

    return valueToReturn;
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
  public String typeInUserNameAlreadyProvided() {
    String existingLoginName = getData().userName();
    page().typeInLoginname(existingLoginName);
    return existingLoginName;
  }

  @Step
  public void verifySuccessfulRegistrationMessageIsDisplayed() {
    getWait().until(ExpectedConditions.visibilityOf(page().getMessageWebElement()));
    assertThat(page().getMessageText().trim())
      .as("Successful registration message was nor shown or had unexpected content.")
      .startsWith(SUCCESSFUL_REGISTRATION_MESSAGE_PREFIX);
  }

  @Step
  public void checkFailedRegistrationMessageIsDisplayed() {
    getWait()
      .until(ExpectedConditions.visibilityOf(page().getUserAlreadyExistsMessageWebElement()));
    assertThat(page().getUserAlreadyExistsMessageText().trim())
      .as("Failed registration message was nor shown or had unexpected content.")
      .startsWith(FAILED_REGISTRATION_MESSAGE_PREFIX);
  }

  @Step
  public void verifySuccessfulRegistrationMessageContainsNewUsername(String loginnameUsed) {
    assertThat(page().getMessageText().trim())
      .as("Successful registration message was expected to contain the new username used.")
      .contains(loginnameUsed);
  }

  //TODO: Think about generics etc instead of this.
  private RegistrationPage page() {
    return getPage(RegistrationPage.class);
  }
}
