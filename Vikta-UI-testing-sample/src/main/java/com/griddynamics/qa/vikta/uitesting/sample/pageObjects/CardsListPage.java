package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardsListPage {

  @FindBy(css = "#tblCards > tbody > tr > td:nth-child(2) > a")
  private List<WebElement> cardNumberHyperLinksList;

  @FindBy(css = "#tblCards > tbody")
  private WebElement cardList;

  @FindBy(id = "btnDelete2")
  private WebElement deleteCardButton;

  @FindBy(id = "divMsgOrErr")
  private WebElement deleteCardMessage;

  @FindBy(id = "aBack")
  private WebElement goToListOfCardsButton;

  public void clickGoToListOfCardsButton() {
    goToListOfCardsButton.click();
  }

  public WebElement getDeletedCardMessageWebElement() {
    return deleteCardMessage;
  }

  public String getDeletedCardMessageText() {
    return deleteCardMessage.getText();
  }

  public void clickDeleteCardButton() {
    deleteCardButton.click();
  }

  public List<WebElement> getEveryCard() {
    return cardList.findElements(By.cssSelector("#tblCards > tbody > tr"));
  }

  public List<WebElement> getParticularCard(String numberOfCard) {
    String selector = String.format("#tblCards > tbody > tr:nth-child(%s)", numberOfCard);
    return cardList.findElements(By.cssSelector(selector));
  }

  public List<WebElement> getCardNumberHyperLinksList() {
    return cardNumberHyperLinksList;
  }
}
