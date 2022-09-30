package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import com.griddynamics.qa.vikta.uitesting.sample.config.DataProvider;
import com.griddynamics.qa.vikta.uitesting.sample.config.TestDataAndProperties;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.AddAddressPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.AddressesPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.BasePage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.CardAddPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.CardsListPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.HomePage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.ItemDetailsPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.LoginPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.RegistrationPage;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.ShoppingCartPage;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

/**
 * Base class to contain common auxiliary methods for step definitions.
 */
@RequiredArgsConstructor
@Component
abstract class BaseSteps {

  private WebDriver webDriver;
  private WebDriverWait wait;
  protected BasePage basePage;
  protected HomePage homePage;
  protected AddAddressPage addAddressPage;
  protected AddressesPage addressesPage;
  protected LoginPage loginPage;
  protected CardAddPage addCardPage;
  protected CardsListPage cardListPage;
  protected ItemDetailsPage itemDetailsPage;
  protected RegistrationPage registrationPage;
  protected ShoppingCartPage shoppingCartPage;

  protected WebDriver getWebDriver() {
    return webDriver;
  }

  WebDriverWait getWait() {
    if (Objects.isNull(this.wait)) {
      this.wait = new WebDriverWait(getWebDriver(), getData().waitTimeout());
    }

    return wait;
  }

  TestDataAndProperties getData() {
    return DataProvider.get();
  }

  void verifyCurrentPageIsHomePageForTheUser(String username) {
    BasePage currentPage = basePage;
    getWait().until(ExpectedConditions.visibilityOf(currentPage.getLoggedInName()));

    assertCurrentPageUrl(getData().baseUrl(), "Home page was expected to be the current one.");

    Assertions
      .assertThat(currentPage.getCurrentUserName())
      .as("Unexpected current user's name displayed. Expected: %s", username)
      .contains(username);
    //TODO: Assert displayed role as well.
  }

  void assertCurrentPageUrl(String expectedUrl, String messageOnFail) {
    Assertions.assertThat(getWebDriver().getCurrentUrl()).as(messageOnFail).contains(expectedUrl);
  }
}
