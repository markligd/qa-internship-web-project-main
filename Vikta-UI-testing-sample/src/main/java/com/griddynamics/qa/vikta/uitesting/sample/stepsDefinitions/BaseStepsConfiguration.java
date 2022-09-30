package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

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
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BaseStepsConfiguration {

  private final DriverManager driverManager;

  @Bean
  BasePage basePage() {
    return getPage(BasePage.class);
  }

  @Bean
  HomePage homePage() {
    return getPage(HomePage.class);
  }

  @Bean
  LoginPage loginPage() {
    return getPage(LoginPage.class);
  }

  @Bean
  AddressesPage addressesPage() {
    return getPage(AddressesPage.class);
  }

  @Bean
  AddAddressPage addAddressPage() {
    return getPage(AddAddressPage.class);
  }

  @Bean
  ItemDetailsPage itemDetailsPage() {
    return getPage(ItemDetailsPage.class);
  }

  @Bean
  CardAddPage cardAddPage() {
    return getPage(CardAddPage.class);
  }

  @Bean
  RegistrationPage registrationPage() {
    return getPage(RegistrationPage.class);
  }

  @Bean
  CardsListPage cardsListPage() {
    return getPage(CardsListPage.class);
  }

  @Bean
  ShoppingCartPage shoppingCartPage() {
    return getPage(ShoppingCartPage.class);
  }

  <P> P getPage(Class<P> pageClass) {
    return PageFactory.initElements(driverManager.get(), pageClass);
  }
}
