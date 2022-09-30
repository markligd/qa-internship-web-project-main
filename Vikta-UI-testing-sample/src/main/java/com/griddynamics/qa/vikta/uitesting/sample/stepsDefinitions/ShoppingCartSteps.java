package com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartSteps extends BaseSteps {

  private static String SUCCESSFUL_ADDED_ITEM_TO_SHOPPING_CART = "Added to cart. Cart size(qty of unique items)";

  @Step
  public void addItemImageToShoppingCart() {
    homePage.clickFirstItemDetailsButton();
    itemDetailsPage.clickAddToCartButton();
  }

  @Step
  public void goToShoppingCart() {
    shoppingCartPage.goToCart();
  }

  @Step
  public void purchase() {
    shoppingCartPage.clickPurchaseButton();
  }

  @Step
  public String getSummedValueOfOrder() {
    return shoppingCartPage.getTotalSumCart();
  }

  @Step
  public void increaseQuantityOfFirstItemInShoppingCart() {
    shoppingCartPage.clickFirstItemPlusButton();
  }

  @Step
  public void decreaseQuantityOfFirstItemInShoppingCart() {
    shoppingCartPage.clickFirstItemMinusButton();
  }

  @Step
  public void selectDelivery() {
    shoppingCartPage.selectDeliveryOption();
  }

  @Step
  public void selectPayment() {
    shoppingCartPage.selectPaymentOption();
  }

  @Step
  public void clearShoppingCart() {
    shoppingCartPage.clickEmptyButton();
  }

  @Step
  public void checkIfShoppingCartIconIsVisible() {
    getWait().until(ExpectedConditions.visibilityOf(shoppingCartPage.getImageOfShoppingCart()));
    assertThat(shoppingCartPage.getImageOfShoppingCart().isDisplayed()).as(
        "Shopping cart should be displayed").isTrue();
  }

  @Step
  public void checkIfFirstItemIsAddedToCart() {
    assertThat(shoppingCartPage.getNumberOfItemsInCart()).as("Shopping cart should show 1")
        .isEqualTo("1");
  }

  @Step
  public void checkIfSuccessfulAddedItemMessageIsDisplayed() {
    getWait().until(ExpectedConditions.visibilityOf(
        itemDetailsPage.getAddProductConfirmationMessageWebElement()));
    assertThat(itemDetailsPage.getAddProductConfirmationMessageText().trim()).as(
            "Should display successful message, product added to cart.")
        .startsWith(SUCCESSFUL_ADDED_ITEM_TO_SHOPPING_CART);
  }

  @Step
  public void checkIfUserCanBuyProductWithoutSelectedDeliveryOption() {
    assertThat(shoppingCartPage.isAddressSelectorRed()).isTrue();
  }

  @Step
  public void checkIfUserCanBuyProductWithoutSelectedPaymentOption() {
    assertThat(shoppingCartPage.isPaymentSelectorRed()).isTrue();
  }

  @Step
  public void checkIfShoppingCartIsNotVisible() {
    getWait().until(ExpectedConditions.invisibilityOf(shoppingCartPage.getImageOfShoppingCart()));
    assertThat(shoppingCartPage.getCartWebElements().isEmpty()).isTrue();
  }

  @Step
  public void checkIfOrderSummaryIsDisplayed() {
    assertThat(shoppingCartPage.orderSummaryIsDisplayed()).as("Purchase summary is displayed")
        .isTrue();
  }

  @Step
  public void checkIfQuantityOfProductIsIncreased(int number) {
    goToShoppingCart();
    assertThat(shoppingCartPage.getFirstItemQuantity()).as(
        "Quantity of product should be more than 1").isEqualTo(number);
  }

  @Step
  public void checkIfQuantityOfProductIsDecreased(int number) {
    goToShoppingCart();
    assertThat(shoppingCartPage.getFirstItemQuantity()).as("Quantity of product should equal to 0")
        .isEqualTo(number);
  }

  @Step
  public void checkIfTotalValuesOfOrderAreEquals(String value) {
    assertThat(shoppingCartPage.getFinalOrderValue().replace("Total: ", "")).as(
        "Values of order should be equal").isEqualTo(value);
  }
}
