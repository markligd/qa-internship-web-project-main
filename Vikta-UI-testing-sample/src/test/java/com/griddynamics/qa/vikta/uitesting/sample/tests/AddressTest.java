package com.griddynamics.qa.vikta.uitesting.sample.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddressTest extends BaseTest {

  @BeforeClass
  public void loginToAccount() {
    loginSteps.openLoginPage();
    loginSteps.loginAsRegularUser();
  }

  @Test
  public void testIfUserIsAbleToAddAddress() {
    addressSteps.clickAddAddressTab();
    addressSteps.fillInAddAddressForm();
    addressSteps.clickSaveButton();
    addressSteps.verifySuccessfulAddAddressMessageIsDisplayed();
  }
}
