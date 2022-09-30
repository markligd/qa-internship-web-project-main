package com.griddynamics.qa.vikta.uitesting.sample.tests.ui;

import com.griddynamics.qa.vikta.uitesting.sample.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShoppingCartTest extends BaseTest {

  @BeforeClass
  public void loginToAccount() {
    loginSteps.openLoginPage();
    loginSteps.loginAsRegularUser();
  }

  @Test
  public void checkIfUserCanAddItemToCartAndPurchaseIt() {
    shoppingCartSteps.addItemImageToShoppingCart();
    shoppingCartSteps.checkIfSuccessfulAddedItemMessageIsDisplayed();
    shoppingCartSteps.checkIfShoppingCartIconIsVisible();
    shoppingCartSteps.checkIfFirstItemIsAddedToCart();
    shoppingCartSteps.goToShoppingCart();
    shoppingCartSteps.selectDelivery();
    shoppingCartSteps.selectPayment();
    shoppingCartSteps.purchase();
    shoppingCartSteps.checkIfOrderSummaryIsDisplayed();
    shoppingCartSteps.clearShoppingCart();
  }

  @Test
  public void canNotBuyItemWithoutChosenDelivery() {
    shoppingCartSteps.addItemImageToShoppingCart();
    shoppingCartSteps.checkIfShoppingCartIconIsVisible();
    shoppingCartSteps.checkIfFirstItemIsAddedToCart();
    shoppingCartSteps.goToShoppingCart();
    shoppingCartSteps.selectPayment();
    shoppingCartSteps.purchase();
    shoppingCartSteps.checkIfUserCanBuyProductWithoutSelectedDeliveryOption();
    shoppingCartSteps.clearShoppingCart();
  }

  @Test
  public void canNotBuyItemWithoutChosenPayment() {
    shoppingCartSteps.addItemImageToShoppingCart();
    shoppingCartSteps.checkIfShoppingCartIconIsVisible();
    shoppingCartSteps.checkIfFirstItemIsAddedToCart();
    shoppingCartSteps.goToShoppingCart();
    shoppingCartSteps.selectDelivery();
    shoppingCartSteps.purchase();
    shoppingCartSteps.checkIfUserCanBuyProductWithoutSelectedPaymentOption();
    shoppingCartSteps.clearShoppingCart();
  }

  @Test
  public void checkIfUserCanIncreaseQuantityOfItem() {
    shoppingCartSteps.addItemImageToShoppingCart();
    shoppingCartSteps.checkIfShoppingCartIconIsVisible();
    shoppingCartSteps.checkIfFirstItemIsAddedToCart();
    shoppingCartSteps.goToShoppingCart();
    shoppingCartSteps.increaseQuantityOfFirstItemInShoppingCart();
    shoppingCartSteps.checkIfQuantityOfProductIsIncreased(2);
    shoppingCartSteps.clearShoppingCart();
  }

  @Test
  public void checkIfUserCanDecreaseQuantityOfItem() {
    shoppingCartSteps.addItemImageToShoppingCart();
    shoppingCartSteps.checkIfShoppingCartIconIsVisible();
    shoppingCartSteps.checkIfFirstItemIsAddedToCart();
    shoppingCartSteps.goToShoppingCart();
    shoppingCartSteps.decreaseQuantityOfFirstItemInShoppingCart();
    shoppingCartSteps.checkIfQuantityOfProductIsDecreased(0);
    shoppingCartSteps.clearShoppingCart();
  }

  @Test
  public void checkIfTotalValueInCartIsEqualToSummaryOrderValue() {
    shoppingCartSteps.addItemImageToShoppingCart();
    shoppingCartSteps.checkIfSuccessfulAddedItemMessageIsDisplayed();
    shoppingCartSteps.checkIfShoppingCartIconIsVisible();
    shoppingCartSteps.checkIfFirstItemIsAddedToCart();
    shoppingCartSteps.goToShoppingCart();
    shoppingCartSteps.selectDelivery();
    shoppingCartSteps.selectPayment();
    String totalValue = shoppingCartSteps.getSummedValueOfOrder();
    shoppingCartSteps.purchase();
    shoppingCartSteps.checkIfOrderSummaryIsDisplayed();
    shoppingCartSteps.checkIfTotalValuesOfOrderAreEquals(totalValue);
    shoppingCartSteps.clearShoppingCart();
  }

  @Test
  public void checkIfUserCanCleanShoppingCartAndCheckIfCartIconIsNotVisibleAfterCleaning() {
    shoppingCartSteps.addItemImageToShoppingCart();
    shoppingCartSteps.checkIfShoppingCartIconIsVisible();
    shoppingCartSteps.checkIfFirstItemIsAddedToCart();
    shoppingCartSteps.goToShoppingCart();
    shoppingCartSteps.clearShoppingCart();
    shoppingCartSteps.checkIfShoppingCartIsNotVisible();
  }
}
