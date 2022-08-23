package com.griddynamics.qa.vikta.uitesting.sample.tests;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.RegistrationPage;
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
  public void testRegularUserIsAbleToRegisterAndLogin() {
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


    loginSteps.openLoginPage();
    loginSteps.login(loginName, password);
    loginSteps.checkIfCurrentPageIsHomePageForCreatedUser(loginName);
  }

  @Test
  public void canNotRegisterExistingAccountName() {
    registrationSteps.openRegistrationPage();

    String loginName = registrationSteps.typeInExistingUsername();
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
