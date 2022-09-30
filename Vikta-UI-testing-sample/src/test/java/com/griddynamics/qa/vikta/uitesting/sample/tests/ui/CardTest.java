package com.griddynamics.qa.vikta.uitesting.sample.tests.ui;

import com.griddynamics.qa.vikta.uitesting.sample.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CardTest extends BaseTest {

  @BeforeClass
  public void loginToAccount() {
    loginSteps.openLoginPage();
    loginSteps.loginAsRegularUser();
  }

  @Test
  public void checkIfUserCanAddAndDeleteCard() {
    cardSteps.clickAddCardTab();

    String cardNumber = cardSteps.fillInAddCardFormWithCardNumber();
    cardSteps.fillInAddCardFormWithCardCode();
    cardSteps.fillInAddCardFormWithOwner();
    cardSteps.fillInAddCardFormWithCardExpirationDate();
    cardSteps.fillInAddCardFormWithCardNickname();

    cardSteps.clickSaveButton();
    cardSteps.verifySuccessfulAddCardMessageIsDisplayed();

    cardSteps.clickGoToCardListTab();
    cardSteps.checkIfParticularAddressContainsCardNumber("222222222222222");

    cardSteps.checkIfNewCardIsAddedToCardListPage(cardNumber);
    cardSteps.deleteLastAddedCard();
    cardSteps.verifySuccessfulDeleteCardMessage();
    cardSteps.verifyIfCardWasDeleted(cardNumber);
  }
}
