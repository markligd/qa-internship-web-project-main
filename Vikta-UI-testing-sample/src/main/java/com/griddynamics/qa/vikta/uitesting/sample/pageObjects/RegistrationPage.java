package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object of Vikta's Registration page
 */
public class RegistrationPage extends BasePage {

  @FindBy(id = "tbLoginName")
  private WebElement loginName;

  @FindBy(id = "tbSurname")
  private WebElement surname;

  @FindBy(id = "tbFirstName")
  private WebElement firstName;

  @FindBy(id = "tbMiddleName")
  private WebElement middleName;

  //email added
  @FindBy(id = "tbEmail")
  private WebElement email;

  @FindBy(id = "tbPassword")
  private WebElement password;

  //co to jest?
  @FindBy(id = "btnSubmitGoToHome")
  private WebElement goToHomeButton;

  @FindBy(id = "tSuccessMessage")
  private WebElement successMessage;

  //register button added, user already exists also

  @FindBy(id = "btnRegister")
  private WebElement registerButton;

  @FindBy(id = "lLoginName")
  private WebElement loginNameMessage;

  public void typeInLoginname(String value) {
    typeIn(value, loginName);
  }

  public void typeInSurname(String value) {
    typeIn(value, surname);
  }

  public void typeInFirstname(String value) {
    typeIn(value, firstName);
  }

  public void typeInPatronim(String value) {
    typeIn(value, middleName);
  }

  public void typeInEmail(String value) {
    typeIn(value, email);
  }

  public void typeInPassword(String value) {
    typeIn(value, password);
  }

  private void typeIn(String value, WebElement targetElement) {
    targetElement.clear();
    targetElement.sendKeys(value);
  }

  public String getMessageText() {
    return successMessage.getText();
  }

  public WebElement getMessageWebElement() {
    return successMessage;
  }

  public String getUserAlreadyExistsMessageText() {
    return loginNameMessage.getText();
  }

  public WebElement getUserAlreadyExistsMessageWebElement() {
    return loginNameMessage;
  }

  public void clickRegisterUserButton() {
    registerButton.click();
  }
}
