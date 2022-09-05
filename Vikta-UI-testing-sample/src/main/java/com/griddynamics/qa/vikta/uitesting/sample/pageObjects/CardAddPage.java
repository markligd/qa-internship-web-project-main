package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardAddPage {

    @FindBy(id = "tbNumber")
    private WebElement cardNumber;

    @FindBy(id = "tbCode")
    private WebElement cardCode;

    @FindBy(id = "tbOwner")
    private WebElement cardOwner;

    @FindBy(id = "tbexpirationDate")
    private WebElement cardExpirationDate;

    @FindBy(id = "tbNickname")
    private WebElement nickname;

    @FindBy(id = "btnSave")
    private WebElement saveButton;

    @FindBy(id = "btnReset")
    private WebElement clearButton;

    @FindBy(id = "aBack")
    private WebElement goToListOfCardsButton;

    @FindBy(id = "divMsgOrErr")
    private WebElement cardCreationMessage;

    public String getCreationMessageText() {
        return cardCreationMessage.getText();
    }

    public WebElement getCreationMessageWebElement() {
        return cardCreationMessage;
    }

    private void typeIn(String value, WebElement targetElement) {
        targetElement.clear();
        targetElement.sendKeys(value);
    }

    public void typeInCardNumber(String number) {
        typeIn(number, cardNumber);
    }

    public void typeInCardCode(String code) {
        typeIn(code, cardCode);
    }

    public void typeInCardOwner(String owner) {
        typeIn(owner, cardOwner);
    }

    public void typeInExpirationDate(String date) {
        typeIn(date, cardExpirationDate);
    }

    public void typeInNickname(String name) {
        typeIn(name, nickname);
    }

    public void clickGoToCardsListPage() {
        goToListOfCardsButton.click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void clickResetClear() {
        clearButton.click();
    }
}
