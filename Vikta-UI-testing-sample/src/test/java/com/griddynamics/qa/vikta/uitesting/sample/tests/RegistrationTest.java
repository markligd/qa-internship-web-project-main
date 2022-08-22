package com.griddynamics.qa.vikta.uitesting.sample.tests;

import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.LoginSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.RegistrationSteps;
import lombok.val;
import org.testng.annotations.Test;

/**
 * Feature: User registration
 * As a guest user
 * I should be able to register new user account(sign-up) and use it to login into the application
 */
public class RegistrationTest extends BaseTest {

  /**
   * Scenario: Regular user is able to login
   */
  @Test(groups = { "smoke", "signup" })
  public void testRegularUserIsAbleToLogin() {
    registrationSteps.openRegistrationPage();

    String loginName = registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.LOGINNAME);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.SURNAME);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.FIRSTNAME);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.PATRONIM);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.EMAIL);
    String password = registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.PASSWORD);

    registrationSteps.clickRegisterUserButton();
    registrationSteps.verifyCurrentPageIsRegistration();
    registrationSteps.verifySuccessfulRegistrationMessageIsDisplayed();
    registrationSteps.verifySuccessfulRegistrationMessageContainsNewUsername(loginName);
    // TODO: Develop the rest of the scenario. E.g. login as new user etc.

    loginSteps.openLoginPage();
    loginSteps.login(loginName, password);
  }

  @Test
  public void testImpossibleToReUseAccountNameForRegistration() {
    registrationSteps.openRegistrationPage();

    val loginName = registrationSteps.typeInUserNameAlreadyProvided();
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.SURNAME);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.FIRSTNAME);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.PATRONIM);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.EMAIL);
    registrationSteps.typeRandomValueInto(RegistrationSteps.FieldName.PASSWORD);

    registrationSteps.clickRegisterUserButton();
    registrationSteps.verifyCurrentPageIsRegistration();
    registrationSteps.checkFailedRegistrationMessageIsDisplayed();
  }
}
