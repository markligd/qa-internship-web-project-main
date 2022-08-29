package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressesPage {

  @FindBy(css = "#tblAddresses > tbody > tr:last-child > td:nth-child(7)")
  private WebElement lastAddedAddressNickname;

  public WebElement getLastAddedAddressNickname() {
    return lastAddedAddressNickname;
  }

  public String getLastAddedAddressNicknameText() {
    return lastAddedAddressNickname.getText();
  }
}
