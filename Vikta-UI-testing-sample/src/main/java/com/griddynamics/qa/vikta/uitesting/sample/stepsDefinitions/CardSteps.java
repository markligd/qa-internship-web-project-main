package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.CardAddPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.CardsListPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.HomePage;
import com.griddynamics.qa.vikta.uitesting.sample.utils.StringHelper;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class CardSteps {

  private static String SUCCESSFUL_ADD_CARD_MESSAGE = "Created payment card";
  private static String SUCCESSFUL_DELETE_CARD_MESSAGE = "Deleted";

  @Autowired
  private HomePage homePage;

  @Autowired
  private CardAddPage addCardPage;

  @Autowired
  private CardsListPage cardListPage;

  @Step
  public void clickAddCardTab() {
    homePage.clickAddCard();
  }

  @Step
  public void clickCardTab() {
    homePage.clickCards();
  }

  @Step
  public String fillInAddCardFormWithCardNumber() {
    String randomCardNumber = StringHelper.generateRandomCardNumber();
    addCardPage.typeInCardNumber(randomCardNumber);
    return randomCardNumber;
  }

  @Step
  public String fillInAddCardFormWithCardCode() {
    String randomCardCode = StringHelper.generateRandomCardCode();
    addCardPage.typeInCardCode(randomCardCode);
    return randomCardCode;
  }

  @Step
  public String fillInAddCardFormWithOwner() {
    String randomCardOwner = StringHelper.generateRandomOwner();
    addCardPage.typeInCardOwner(randomCardOwner);
    return randomCardOwner;
  }

  @Step
  public String fillInAddCardFormWithCardExpirationDate() {
    String randomCardExpirationDate = StringHelper.generateRandomCardExpirationDate();
    addCardPage.typeInExpirationDate(randomCardExpirationDate);
    return randomCardExpirationDate;
  }

  @Step
  public String fillInAddCardFormWithCardNickname() {
    String randomCardNickname = StringHelper.generateRandomNickname();
    addCardPage.typeInNickname(randomCardNickname);
    return randomCardNickname;
  }

  @Step
  public void clickSaveButton() {
    addCardPage.clickSaveButton();
  }

  @Step
  public void clickGoToCardListTab() {
    addCardPage.clickGoToCardsListPage();
  }

  @Step
  public void verifySuccessfulAddCardMessageIsDisplayed() {
    Assertions
      .assertThat(addCardPage.getCreationMessageText().trim())
      .as("Successful add card message was nor shown or had unexpected content.")
      .startsWith(SUCCESSFUL_ADD_CARD_MESSAGE);
  }

  @Step
  public void checkIfNewCardIsAddedToCardListPage(String cardNumber) {
    List<String> listOfCards = cardListPage
      .getEveryCard()
      .stream()
      .map(WebElement::getText)
      .collect(Collectors.toList());
    assertThat(listOfCards).anyMatch(card -> card.contains(cardNumber));
  }

  @Step
  public void checkIfParticularAddressContainsCardNumber(String cardNumber) {
    List<String> listOfAddresses = cardListPage
      .getParticularCard("2")
      .stream()
      .map(WebElement::getText)
      .collect(Collectors.toList());
    assertThat(listOfAddresses).anyMatch(address -> address.contains(cardNumber));
  }

  @Step
  public void deleteLastAddedCard() {
    List<WebElement> addressHyperLinksList = cardListPage.getCardNumberHyperLinksList();
    addressHyperLinksList.get(addressHyperLinksList.size() - 1).click();
    cardListPage.clickDeleteCardButton();
  }

  @Step
  public void verifySuccessfulDeleteCardMessage() {
    Assertions
      .assertThat(cardListPage.getDeletedCardMessageText().trim())
      .as("Successful deleted card message was nor shown or had unexpected content.")
      .startsWith(SUCCESSFUL_DELETE_CARD_MESSAGE);
  }

  @Step
  public void verifyIfCardWasDeleted(String cardNumber) {
    cardListPage.clickGoToListOfCardsButton();
    List<String> listOfAddresses = cardListPage
      .getEveryCard()
      .stream()
      .map(WebElement::getText)
      .collect(Collectors.toList());
    assertThat(listOfAddresses).noneMatch(address -> address.contains(cardNumber));
  }
}
