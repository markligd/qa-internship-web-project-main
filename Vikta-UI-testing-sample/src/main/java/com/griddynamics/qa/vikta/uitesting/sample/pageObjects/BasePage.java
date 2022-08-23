package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * "Parent" Page Object for almost all of the rest of the pages (except Login).
 * <p>
 * More to read:
 * https://selenium.dev/documentation/en/guidelines_and_recommendations/page_object_models/
 * https://martinfowler.com/bliki/PageObject.html
 * https://www.baeldung.com/selenium-webdriver-page-object
 * https://www.pluralsight.com/guides/getting-started-with-page-object-pattern-for-your-selenium-tests
 */
public class BasePage {

  @FindBy(id = "sploggedInName")
  private WebElement loggedInName;

  @FindBy(id = "aLogoutTop")
  private WebElement logoutTop;

  @FindBy(id = "aEditProfile")
  private WebElement editProfile;

  @FindBy(id = "aAddCard")
  private WebElement addCard;

  @FindBy(id = "aCard")
  private WebElement card;

  @FindBy(id = "aAddAddress")
  private WebElement addAddress;

  @FindBy(id = "aAddresses")
  private WebElement addresses;

  @FindBy(id = "aHome")
  private WebElement home;

  @FindBy(id = "aLogoutBottom")
  private WebElement logoutBottom;

  @FindBy(id = "aToCartTop")
  private WebElement toCartTop;

  @FindBy(id = "spCartTopMsg")
  private WebElement cartTopMsg;

  public String getCurrentUserName() {
    return loggedInName.getText();
  }

  public WebElement getLoggedInName() {
    return loggedInName;
  }

  public void clickLogout() {
    logoutTop.click();
  }

  public void clickHome() {
    home.click();
  }

  public void goToCart() {
    toCartTop.click();
  }

  public void clickAddresses() {
    addresses.click();
  }

  public void clickAddAddress() {
    addAddress.click();
  }

  public void clickLogoutBottom() {
    logoutBottom.click();
  }

  public void clickAddCard() {
    addCard.click();
  }

  public void clickEditProfile() {
    editProfile.click();
  }

  public void clickCards() {
    card.click();
  }

  public String getNumberOfItemsInCart() {
    return cartTopMsg.getText();
  }
}
