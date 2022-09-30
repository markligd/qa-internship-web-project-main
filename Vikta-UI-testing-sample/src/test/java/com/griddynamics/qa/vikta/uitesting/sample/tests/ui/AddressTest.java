package com.griddynamics.qa.vikta.uitesting.sample.tests.ui;

import com.griddynamics.qa.vikta.uitesting.sample.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddressTest extends BaseTest {

  @BeforeClass
  public void loginToAccount() {
    loginSteps.openLoginPage();
    loginSteps.loginAsRegularUser();
  }

  @Test
  public void testIfUserIsAbleToAddAndDeleteAddress() {
    addressSteps.clickAddAddressTab();

    String streetName = addressSteps.fillInAddAddressFormWithStreetName();
    addressSteps.fillInAddAddressFormWithAdditionalStreetInfo();
    addressSteps.fillInAddAddressFormWithCityName();
    addressSteps.fillInAddAddressFormWithRegion();
    addressSteps.fillInAddAddressFormWithPostalCode();
    addressSteps.fillInAddAddressFormWithNickname();

    addressSteps.clickSaveButton();
    addressSteps.verifySuccessfulAddAddressMessageIsDisplayed();

    addressSteps.clickAddressesTab();
    addressSteps.checkIfParticularAddressContainsStreetName("krakowska");

    addressSteps.checkIfNewAddressIsAddedToAddresses(streetName);
    addressSteps.deleteLastAddedAddress();
    addressSteps.verifySuccessfulDeleteMessage();
    addressSteps.verifyIfAddressWasDeleted(streetName);
  }
}
