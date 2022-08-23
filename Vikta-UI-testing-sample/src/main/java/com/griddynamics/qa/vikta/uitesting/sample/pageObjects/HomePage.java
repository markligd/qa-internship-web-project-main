package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object of Home page
 */
public class HomePage extends BasePage {

  @FindBy(id = "btnResetSearchCriteria")
  private WebElement resetSearchCriteriaButton;

  @FindBy(id = "btnSearch")
  private WebElement searchButton;

  @FindBy(id = "tbTerm")
  private WebElement term;

  @FindBy(id = "tbRatingFrom")
  private WebElement ratingFrom;

  @FindBy(id = "tbRatingTo")
  private WebElement ratingTo;

  @FindBy(id = "tbPriceFrom")
  private WebElement priceFrom;

  @FindBy(id = "tbPriceTo")
  private WebElement priceTo;

  private void typeIn(String value, WebElement targetElement) {
    targetElement.clear();
    targetElement.sendKeys(value);
  }

  public void typeInRatingFrom(String ratingFrom) {
    typeIn(ratingFrom, this.ratingFrom);
  }

  public void typeInRatingTo(String ratingTo) {
    typeIn(ratingTo, this.ratingTo);
  }

  public void typeInPriceFrom(String priceFrom) {
    typeIn(priceFrom, this.priceFrom);
  }

  public void typeInPriceTo(String priceTo) {
    typeIn(priceTo, this.priceTo);
  }

  public void clickSearchButton() {
    searchButton.click();
  }

  public void clickResetButton() {
    resetSearchCriteriaButton.click();
  }
}
