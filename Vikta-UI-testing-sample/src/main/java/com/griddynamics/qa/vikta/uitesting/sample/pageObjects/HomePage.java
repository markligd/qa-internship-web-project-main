package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import org.openqa.selenium.By;
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

  @FindBy(css = ".products-list")
  private WebElement productsList;

  @FindBy(css = ":nth-child(1)> nav > a")
  private WebElement firstItemDetailsButton;

  public void clickFirstItemDetailsButton() {
    firstItemDetailsButton.click();
  }

  private void typeIn(String value, WebElement targetElement) {
    targetElement.clear();
    targetElement.sendKeys(value);
  }

  public void typeInRatingFrom(String ratingFromValue) {
    typeIn(ratingFromValue, ratingFrom);
  }

  public void typeInRatingTo(String ratingToValue) {
    typeIn(ratingToValue, ratingTo);
  }

  public void typeInPriceFrom(String priceFromValue) {
    typeIn(priceFromValue, priceFrom);
  }

  public void typeInPriceTo(String priceToValue) {
    typeIn(priceToValue, priceTo);
  }

  public void clickSearchButton() {
    searchButton.click();
  }

  public void clickResetButton() {
    resetSearchCriteriaButton.click();
  }

  public void typeInTermToSearchBar(String searchBarTerm) {
    typeIn(searchBarTerm, term);
  }

  public List<WebElement> getProductsTitlesFromCurrentPage() {
    return productsList.findElements(By.className("product-card__title"));
  }
}
