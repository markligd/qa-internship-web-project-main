package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.AddAddressPage;
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
  public void fillInAddAddressForm() {
    String randomStreetName = StringHelper.generateRandomStreetName();
    addAddressPage().typeInStreetName(randomStreetName);

    String randomAdditionalStreetInfo = StringHelper.generateRandomAdditionalStreetInfo();
    addAddressPage().typeInStreetAdditional(randomAdditionalStreetInfo);

    String randomCityName = StringHelper.generateRandomCityName();
    addAddressPage().typeInCityName(randomCityName);

    String randomRegion = StringHelper.generateRandomRegion();
    addAddressPage().typeInRegionName(randomRegion);

    String randomPostalCode = StringHelper.generateRandomPostalCode();
    addAddressPage().typeInPostalCode(randomPostalCode);

    String randomNickname = StringHelper.generateRandomAddressNickname();
    addAddressPage().typeInAddressNickname(randomNickname);
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

  private HomePage homePage() {
    return getPage(HomePage.class);
  }

  private AddAddressPage addAddressPage() {
    return getPage(AddAddressPage.class);
  }
}
