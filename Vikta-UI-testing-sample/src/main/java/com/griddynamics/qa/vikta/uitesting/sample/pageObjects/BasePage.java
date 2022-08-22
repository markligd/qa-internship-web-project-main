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
  private WebElement ploggedInName;

  @FindBy(id = "aLogoutTop")
  private WebElement LogoutTop;

  @FindBy(id = "aEditProfile")
  private WebElement EditProfile;

  @FindBy(id = "aAddCard")
  private WebElement AddCard;

  @FindBy(id = "aCard")
  private WebElement Card;

  @FindBy(id = "aAddAddress")
  private WebElement AddAddress;

  @FindBy(id = "aAddresses")
  private WebElement Addresses;

  @FindBy(id = "aHome")
  private WebElement Home;

  @FindBy(id = "aLogoutBottom")
  private WebElement LogoutBottom;

  @FindBy(id = "aToCartTop")
  private WebElement ToCartTop;

  @FindBy(id = "spCartTopMsg")
  private WebElement CartTopMsg;

  public String getCurrentUserName() {
    return ploggedInName.getText();
  }

  public WebElement getLoggedInName() {
    return ploggedInName;
  }

  public void clickLogout() {
    LogoutTop.click();
  }

  public void clickHome() {
    Home.click();
  }

  public void goToCart() {
    ToCartTop.click();
  }

  public void clickAddresses() {
    Addresses.click();
  }

  public void clickAddAddress() {
    AddAddress.click();
  }

  public void clickLogoutBottom() {
    LogoutBottom.click();
  }

  public void clickAddCard() {
    AddCard.click();
  }

  public void clickEditProfile() {
    EditProfile.click();
  }

  public void clickCards() {
    Card.click();
  }

  public String getNumberOfItemsInCart() {
    return CartTopMsg.getText();
  }
}
//dodac scrolla
