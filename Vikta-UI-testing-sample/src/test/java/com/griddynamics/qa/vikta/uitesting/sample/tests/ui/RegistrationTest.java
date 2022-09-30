package com.griddynamics.qa.vikta.uitesting.sample.tests.ui;

import com.griddynamics.qa.vikta.uitesting.sample.tests.BaseTest;
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
  public void testRegularUserIsAbleToRegisterAndLogin() {
    registrationSteps.openRegistrationPage();

    String loginName = registrationSteps.fillInRegistrationFormWithLoginName();
    registrationSteps.fillInRegistrationFormWithSurname();
    registrationSteps.fillInRegistrationFormWithFirstName();
    registrationSteps.fillInRegistrationFormWithPatronim();
    registrationSteps.fillInRegistrationFormWithEmail();
    String password = registrationSteps.fillInRegistrationFormWithPassword();

    registrationSteps.clickRegisterUserButton();
    registrationSteps.verifyCurrentPageIsRegistration();
    registrationSteps.verifySuccessfulRegistrationMessageIsDisplayed();
    registrationSteps.verifySuccessfulRegistrationMessageContainsNewUsername(loginName);

    loginSteps.openLoginPage();
    loginSteps.login(loginName, password);
    loginSteps.checkIfCurrentPageIsHomePageForCreatedUser(loginName);
  }

  @Test
  public void canNotRegisterExistingAccountName() {
    registrationSteps.openRegistrationPage();

    registrationSteps.typeInExistingUsername();
    registrationSteps.fillInRegistrationFormWithSurname();
    registrationSteps.fillInRegistrationFormWithFirstName();
    registrationSteps.fillInRegistrationFormWithPatronim();
    registrationSteps.fillInRegistrationFormWithEmail();
    registrationSteps.fillInRegistrationFormWithPassword();

    registrationSteps.clickRegisterUserButton();
    registrationSteps.verifyCurrentPageIsRegistration();
    registrationSteps.checkFailedRegistrationMessageIsDisplayed();
  }
}
