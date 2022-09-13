package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemDetailsPage {

    @FindBy(id = "aAddToCart")
    private WebElement addToCartButton;

    @FindBy(id = "tMessage")
    private WebElement addProductConfirmationMessage;

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public WebElement getAddProductConfirmationMessageWebElement() {
        return addProductConfirmationMessage;
    }

    public String getAddProductConfirmationMessageText() {
        return addProductConfirmationMessage.getText();
    }
}
