package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddAddressPage {

  @FindBy(id = "tbStreet")
  private WebElement streetName;

  @FindBy(id = "tbStreetAdditional")
  private WebElement additionalStreetInfo;

  @FindBy(id = "tbCityName")
  private WebElement cityName;

  @FindBy(id = "tbRegionName")
  private WebElement regionName;

  @FindBy(id = "tbPostalCode")
  private WebElement postalCode;

  @FindBy(id = "tbAddressNickname")
  private WebElement addressNickname;

  @FindBy(id = "aBack")
  private WebElement goToAddressesPage;

  @FindBy(id = "btnSave")
  private WebElement saveButton;

  @FindBy(id = "btnReset")
  private WebElement clearButton;

  @FindBy(id = "divMsgOrErr")
  private WebElement successMessage;

  public String getMessageText() {
    return successMessage.getText();
  }

  public WebElement getMessageWebElement() {
    return successMessage;
  }

  public void typeInStreetName(String value) {
    typeIn(value, streetName);
  }

  public void typeInStreetAdditional(String value) {
    typeIn(value, additionalStreetInfo);
  }

  public void typeInCityName(String value) {
    typeIn(value, cityName);
  }

  public void typeInRegionName(String value) {
    typeIn(value, regionName);
  }

  public void typeInPostalCode(String value) {
    typeIn(value, postalCode);
  }

  public void typeInAddressNickname(String value) {
    typeIn(value, addressNickname);
  }

  private void typeIn(String value, WebElement targetElement) {
    targetElement.clear();
    targetElement.sendKeys(value);
  }

  public void clickSaveButton() {
    saveButton.click();
  }

  public void clickClearButton() {
    clearButton.click();
  }
}
