package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.AddAddressPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.AddressesPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.HomePage;
import com.griddynamics.qa.vikta.uitesting.sample.utils.StringHelper;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddressSteps extends BaseSteps {

  private static String SUCCESSFUL_ADD_ADDRESS_MESSAGE = "Created user address";

  public AddressSteps(WebDriver driver) {
    super(driver);
  }

  @Step
  public void clickAddAddressTab() {
    homePage().clickAddAddress();
  }

  @Step
  public void clickAddressesTab() {
    homePage().clickAddresses();
  }

  @Step
  public void fillInAddAddressFormWithStreetName() {
    String randomStreetName = StringHelper.generateRandomStreetName();
    addAddressPage().typeInStreetName(randomStreetName);
  }

  @Step
  public void fillInAddAddressFormWithAdditionalStreetInfo() {
    String randomAdditionalStreetInfo = StringHelper.generateRandomAdditionalStreetInfo();
    addAddressPage().typeInStreetAdditional(randomAdditionalStreetInfo);
  }

  @Step
  public void fillInAddAddressFormWithCityName() {
    String randomCityName = StringHelper.generateRandomCityName();
    addAddressPage().typeInCityName(randomCityName);
  }

  @Step
  public void fillInAddAddressFormWithRegion() {
    String randomRegion = StringHelper.generateRandomRegion();
    addAddressPage().typeInRegionName(randomRegion);
  }

  @Step
  public void fillInAddAddressFormWithPostalCode() {
    String randomPostalCode = StringHelper.generateRandomPostalCode();
    addAddressPage().typeInPostalCode(randomPostalCode);
  }

  @Step
  public String fillInAddAddressFormWithNickname() {
    String randomNickname = StringHelper.generateRandomAddressNickname();
    addAddressPage().typeInAddressNickname(randomNickname);

    return randomNickname;
  }

  @Step
  public void clickSaveButton() {
    addAddressPage().clickSaveButton();
  }

  @Step
  public void verifySuccessfulAddAddressMessageIsDisplayed() {
    getWait().until(ExpectedConditions.visibilityOf(addAddressPage().getMessageWebElement()));
    Assertions
      .assertThat(addAddressPage().getMessageText().trim())
      .as("Successful registration message was nor shown or had unexpected content.")
      .startsWith(SUCCESSFUL_ADD_ADDRESS_MESSAGE);
  }

  @Step
  public void checkIfNewAddressIsAdded(String nickname) {
    getWait().until(ExpectedConditions.visibilityOf(addressesPage().getLastAddedAddressNickname()));
    Assertions
      .assertThat(addressesPage().getLastAddedAddressNicknameText().trim())
      .isEqualTo(nickname);
    //    Assertions
    //      .assertThat(addressesPage().getLastAddedAddressNicknameText().trim())
    //      .contains(nickname);
  }

  private HomePage homePage() {
    return getPage(HomePage.class);
  }

  private AddAddressPage addAddressPage() {
    return getPage(AddAddressPage.class);
  }

  private AddressesPage addressesPage() {
    return getPage(AddressesPage.class);
  }
}
