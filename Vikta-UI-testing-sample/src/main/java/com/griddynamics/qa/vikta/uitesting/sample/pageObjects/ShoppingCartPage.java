package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCartPage extends BasePage {

  @FindBy(css = "body")
  private WebElement body;

  @FindBy(id = "btnPurchase")
  private WebElement purchaseButton;

  @FindBy(id = "btnEmpty")
  private WebElement emptyCartButton;

  @FindBy(id = "slctPayment")
  private WebElement paymentMethodSelector;

  @FindBy(id = "slctAddress")
  private WebElement addressOptionSelector;

  @FindBy(xpath = "//span[@id='spCartTotal']")
  private WebElement totalSumCart;

  @FindBy(name = "btnDec")
  private WebElement decreaseNumberOfProductButton;

  @FindBy(name = "btnInc")
  private WebElement increaseNumberOfProductButton;

  @FindBy(id = "spPurchaseTotal")
  private WebElement totalOrderValue;

  @FindBy(id = "spToBeDeliveredTo")
  private WebElement deliveryAddress;

  @FindBy(id = "spPaidBy")
  private WebElement paymentMethod;

  @FindBy(xpath = "//div[@id='divUponPurchase']")
  private WebElement orderSummaryMessage;

  @FindBy(xpath = "//tbody/tr[2]/td/div/div/div/nav/div/span[1]")
  private WebElement firstItemPrice;

  @FindBy(xpath = "//tbody/tr[2]/td/div/div/div/nav/div/span[3]")
  private WebElement firstItemQuantity;

  public List<WebElement> getCartWebElements() {
    return body.findElements(By.id("aToCartTop"));
  }

  public int getFirstItemQuantity() {
    return Integer.parseInt(firstItemQuantity.getText());
  }

  public void clickFirstItemPlusButton() {
    increaseNumberOfProductButton.click();
  }

  public void clickFirstItemMinusButton() {
    decreaseNumberOfProductButton.click();
  }

  public void clickPurchaseButton() {
    purchaseButton.click();
  }

  public void clickEmptyButton() {
    emptyCartButton.click();
  }

  public void selectDeliveryOption() {
    Select selectObject = new Select(addressOptionSelector);
    selectObject.selectByIndex(1);
  }

  public void selectPaymentOption() {
    Select selectObject = new Select(paymentMethodSelector);
    selectObject.selectByIndex(1);
  }

  public String getTotalSumCart() {
    return totalSumCart.getText();
  }

  public String getFinalOrderValue() {
    return totalOrderValue.getText();
  }

  public String getFinalDeliveryAddress() {
    return deliveryAddress.getText();
  }

  public String getFinalPaymentMethod() {
    return paymentMethod.getText();
  }

  public boolean orderSummaryIsDisplayed() {
    return orderSummaryMessage.isDisplayed();
  }

  public boolean isAddressSelectorRed() {
    return addressOptionSelector.getAttribute("style").equals("color: red;");
  }

  public boolean isPaymentSelectorRed() {
    return paymentMethodSelector.getAttribute("style").equals("color: red;");
  }
}
