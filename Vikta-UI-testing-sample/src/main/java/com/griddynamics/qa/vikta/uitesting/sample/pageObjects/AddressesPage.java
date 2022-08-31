package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressesPage {

  @FindBy(css = "#tblAddresses > tbody > tr > td:nth-child(2) > a")
  private List<WebElement> streetNameHyperLinksList;

  @FindBy(css = "#tblAddresses > tbody")
  private WebElement addressList;

  @FindBy(id = "btnDelete")
  private WebElement deleteButton;

  @FindBy(id = "divMsgOrErr")
  private WebElement deleteMessage;

  @FindBy(id = "aBack")
  private WebElement goBackButton;

  public void clickGoBackButton() {
    goBackButton.click();
  }

  public String getDeletedMessageText() {
    return deleteMessage.getText();
  }

  public WebElement getDeletedMessageWebElement() {
    return deleteMessage;
  }

  public void clickDeleteButton() {
    deleteButton.click();
  }

  public List<WebElement> getEveryAddress() {
    return addressList.findElements(By.cssSelector("#tblAddresses > tbody > tr"));
  }

  public List<WebElement> getParticularAddress(String numberOfAddress) {
    String selector = String.format("#tblAddresses > tbody > tr:nth-child(%s)", numberOfAddress);
    return addressList.findElements(By.cssSelector(selector));
  }

  public List<WebElement> getStreetNameHyperLinksList() {
    return streetNameHyperLinksList;
  }
}
