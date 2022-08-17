package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object of Home page
 */
public class HomePage extends BasePage {

    @FindBy(id = "btnResetSearchCriteria")
    private WebElement btnResetSearchCriteria;

    @FindBy(id = "btnSearch")
    private WebElement btnSearch;

    @FindBy(id = "tbTerm")
    private WebElement tbTerm;

    @FindBy(id = "tbRatingFrom")
    private WebElement tbRatingFrom;

    @FindBy(id = "tbRatingTo")
    private WebElement tbRatingTo;

    @FindBy(id = "tbPriceFrom")
    private WebElement tbPriceFrom;

    @FindBy(id = "tbPriceTo")
    private WebElement tbPriceTo;

    private void typeIn(String value, WebElement targetElement) {
        targetElement.clear();
        targetElement.sendKeys(value);
    }

    public void typeInRatingFrom(String ratingFrom){
        typeIn(ratingFrom, tbRatingFrom);
    }
    public void typeInRatingTo(String ratingTo){
        typeIn(ratingTo, tbRatingTo);
    }
    public void typeInPriceFrom(String priceFrom){
        typeIn(priceFrom, tbPriceFrom);
    }
    public void typeInPriceTo(String priceTo){
        typeIn(priceTo, tbPriceTo);
    }

    public void clickSearchButton(){
        btnSearch.click();
    }

    public void clickResetButton(){
        btnResetSearchCriteria.click();
    }

}
