package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.griddynamics.qa.vikta.uitesting.sample.utils.StringHelper;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

@Component
public class AddressSteps extends BaseSteps {

  private static String SUCCESSFUL_ADD_ADDRESS_MESSAGE = "Created user address";
  private static String SUCCESSFUL_DELETE_ADDRESS_MESSAGE = "Deleted";

  @Step
  public void clickAddAddressTab() {
    homePage.clickAddAddress();
  }

  @Step
  public void clickAddressesTab() {
    homePage.clickAddresses();
  }

  @Step
  public String fillInAddAddressFormWithStreetName() {
    String randomStreetName = StringHelper.generateRandomStreetName();
    addAddressPage.typeInStreetName(randomStreetName);
    return randomStreetName;
  }

  @Step
  public String fillInAddAddressFormWithAdditionalStreetInfo() {
    String randomAdditionalStreetInfo = StringHelper.generateRandomAdditionalStreetInfo();
    addAddressPage.typeInStreetAdditional(randomAdditionalStreetInfo);
    return randomAdditionalStreetInfo;
  }

  @Step
  public String fillInAddAddressFormWithCityName() {
    String randomCityName = StringHelper.generateRandomCityName();
    addAddressPage.typeInCityName(randomCityName);
    return randomCityName;
  }

  @Step
  public String fillInAddAddressFormWithRegion() {
    String randomRegion = StringHelper.generateRandomRegion();
    addAddressPage.typeInRegionName(randomRegion);
    return randomRegion;
  }

  @Step
  public String fillInAddAddressFormWithPostalCode() {
    String randomPostalCode = StringHelper.generateRandomPostalCode();
    addAddressPage.typeInPostalCode(randomPostalCode);
    return randomPostalCode;
  }

  @Step
  public String fillInAddAddressFormWithNickname() {
    String randomNickname = StringHelper.generateRandomAddressNickname();
    addAddressPage.typeInAddressNickname(randomNickname);
    return randomNickname;
  }

  @Step
  public void clickSaveButton() {
    addAddressPage.clickSaveButton();
  }

  @Step
  public void verifySuccessfulAddAddressMessageIsDisplayed() {
    getWait().until(ExpectedConditions.visibilityOf(addAddressPage.getMessageWebElement()));
    Assertions
        .assertThat(addAddressPage.getMessageText().trim())
        .as("Successful add address message was nor shown or had unexpected content.")
        .startsWith(SUCCESSFUL_ADD_ADDRESS_MESSAGE);
  }

  @Step
  public void checkIfNewAddressIsAddedToAddresses(String streetName) {
    List<String> listOfAddresses = addressesPage
        .getEveryAddress()
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
    assertThat(listOfAddresses).anyMatch(address -> address.contains(streetName));
  }

  @Step
  public void checkIfParticularAddressContainsStreetName(String streetName) {
    List<String> listOfAddresses = addressesPage
        .getParticularAddress("2")
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
    assertThat(listOfAddresses).anyMatch(address -> address.contains(streetName));
  }

  @Step
  public void deleteLastAddedAddress() {
    List<WebElement> addressHyperLinksList = addressesPage.getStreetNameHyperLinksList();
    addressHyperLinksList.get(addressHyperLinksList.size() - 1).click();
    addressesPage.clickDeleteButton();
  }

  @Step
  public void verifySuccessfulDeleteMessage() {
    getWait().until(ExpectedConditions.visibilityOf(addressesPage.getDeletedMessageWebElement()));
    Assertions
        .assertThat(addressesPage.getDeletedMessageText().trim())
        .as("Successful deleted address message was nor shown or had unexpected content.")
        .startsWith(SUCCESSFUL_DELETE_ADDRESS_MESSAGE);
  }

  @Step
  public void verifyIfAddressWasDeleted(String streetName) {
    addressesPage.clickGoBackButton();
    List<String> listOfAddresses = addressesPage
        .getEveryAddress()
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
    assertThat(listOfAddresses).noneMatch(address -> address.contains(streetName));
  }
}
