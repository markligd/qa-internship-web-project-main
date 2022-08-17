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
  private WebElement sploggedInName;

  @FindBy(id = "aLogoutTop")
  private WebElement aLogoutTop;

  @FindBy(id = "aEditProfile")
  private WebElement aEditProfile;

  @FindBy(id = "aAddCard")
  private WebElement aAddCard;

  @FindBy(id = "aCard")
  private WebElement aCard;

  @FindBy(id = "aAddAddress")
  private WebElement aAddAddress;

  @FindBy(id = "aAddresses")
  private WebElement aAddresses;

  @FindBy(id = "aHome")
  private WebElement aHome;

  @FindBy(id = "aLogoutBottom")
  private WebElement aLogoutBottom;

  @FindBy(id = "aToCartTop")
  private WebElement aToCartTop;

  @FindBy(id = "spCartTopMsg")
  private WebElement spCartTopMsg;

  public String getCurrentUserName() {
    return sploggedInName.getText();
  }

  public WebElement getLoggedInName() {
    return sploggedInName;
  }


  public void clickLogout() {
    aLogoutTop.click();
  }

  public void clickHome() {
    aHome.click();
  }

  public void goToCart() {
    aToCartTop.click();
  }

  public void clickAddresses() {
    aAddresses.click();
  }

  public void clickAddAddress() {
    aAddAddress.click();
  }

  public void clickLogoutBottom() {
    aLogoutBottom.click();
  }

  public void clickAddCard() {
    aAddCard.click();
  }

  public void clickEditProfile() {
    aEditProfile.click();
  }

  public void clickCards() {
    aCard.click();
  }

  public String getNumberOfItemsInCart() {
    return spCartTopMsg.getText();
  }

}
