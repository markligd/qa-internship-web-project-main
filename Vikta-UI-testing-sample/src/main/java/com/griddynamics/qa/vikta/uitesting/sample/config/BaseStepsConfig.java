package com.griddynamics.qa.vikta.uitesting.sample.config;

import com.griddynamics.qa.vikta.uitesting.sample.auxiliary.DriverManager;
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
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BaseStepsConfig {

  @Bean
  public WebDriver webDriver() {
    DriverManager driverManager = new DriverManager(DataProvider.get());
    driverManager.instantiateDriver();
    return driverManager.get();
  }

  @Bean
  public BasePage basePage() {
    return getPage(BasePage.class);
  }

  @Bean
  public HomePage homePage() {
    return getPage(HomePage.class);
  }

  @Bean
  public AddAddressPage addAddressPage() {
    return getPage(AddAddressPage.class);
  }

  @Bean
  public AddressesPage addressesPage() {
    return getPage(AddressesPage.class);
  }

  @Bean
  public LoginPage loginPage() {
    return getPage(LoginPage.class);
  }

  @Bean
  public CardAddPage addCardPage() {
    return getPage(CardAddPage.class);
  }

  @Bean
  public CardsListPage cardListPage() {
    return getPage(CardsListPage.class);
  }

  @Bean
  public ItemDetailsPage itemDetailsPage() {
    return getPage(ItemDetailsPage.class);
  }

  @Bean
  public RegistrationPage registrationPage() {
    return getPage(RegistrationPage.class);
  }

  @Bean
  public ShoppingCartPage shoppingCartPage() {
    return getPage(ShoppingCartPage.class);
  }

  private <P> P getPage(Class<P> pageClass) {
    return PageFactory.initElements(webDriver(), pageClass);
  }
}
