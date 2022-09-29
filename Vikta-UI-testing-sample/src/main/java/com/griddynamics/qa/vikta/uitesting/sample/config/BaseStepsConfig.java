package com.griddynamics.qa.vikta.uitesting.sample.config;

import com.griddynamics.qa.vikta.uitesting.sample.auxiliary.DriverManager;
import com.griddynamics.qa.vikta.uitesting.sample.pageObjects.*;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseStepsConfig {
    private DriverManager driverManager;


    @Bean
    BasePage basePage() {
        return getPage(BasePage.class);
    }

    @Bean
    HomePage homePage() {
        return getPage(HomePage.class);
    }

    @Bean
    AddAddressPage addAddressPage() {
        return getPage(AddAddressPage.class);
    }

    @Bean
    AddressesPage addressesPage() {
        return getPage(AddressesPage.class);
    }

    @Bean
    LoginPage loginPage() {
        return getPage(LoginPage.class);
    }

    @Bean
    CardAddPage cardAddPage(){
        return getPage(CardAddPage.class);
    }

    @Bean
    CardsListPage cardsListPage(){
        return getPage(CardsListPage.class);
    }
    @Bean
    ItemDetailsPage itemDetailsPage(){
        return getPage(ItemDetailsPage.class);
    }

    @Bean
    RegistrationPage registrationPage(){
        return getPage(RegistrationPage.class);
    }

    @Bean
    ShoppingCartPage shoppingCartPage(){
        return getPage(ShoppingCartPage.class);
    }


    <P> P getPage(Class<P> pageClass) {
        return PageFactory.initElements(driverManager.get(), pageClass);
    }
}
