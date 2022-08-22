package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object of Home page
 */
public class HomePage extends BasePage {

  @FindBy(id = "btnResetSearchCriteria")
  private WebElement ResetSearchCriteria;

  @FindBy(id = "btnSearch")
  private WebElement Search;

  @FindBy(id = "tbTerm")
  private WebElement Term;

  @FindBy(id = "tbRatingFrom")
  private WebElement RatingFrom;

  @FindBy(id = "tbRatingTo")
  private WebElement RatingTo;

  @FindBy(id = "tbPriceFrom")
  private WebElement PriceFrom;

  @FindBy(id = "tbPriceTo")
  private WebElement PriceTo;

  private void typeIn(String value, WebElement targetElement) {
    targetElement.clear();
    targetElement.sendKeys(value);
  }

  public void typeInRatingFrom(String ratingFrom) {
    typeIn(ratingFrom, RatingFrom);
  }

  public void typeInRatingTo(String ratingTo) {
    typeIn(ratingTo, RatingTo);
  }

  public void typeInPriceFrom(String priceFrom) {
    typeIn(priceFrom, PriceFrom);
  }

  public void typeInPriceTo(String priceTo) {
    typeIn(priceTo, PriceTo);
  }

  public void clickSearchButton() {
    Search.click();
  }

  public void clickResetButton() {
    ResetSearchCriteria.click();
  }
}
