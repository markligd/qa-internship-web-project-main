package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object of Vikta's Registration page
 */
public class RegistrationPage extends BasePage {

  @FindBy(id = "tbLoginName")
  private WebElement LoginName;

  @FindBy(id = "tbSurname")
  private WebElement Surname;

  @FindBy(id = "tbFirstName")
  private WebElement FirstName;

  @FindBy(id = "tbMiddleName")
  private WebElement MiddleName;

  //email added
  @FindBy(id = "tbEmail")
  private WebElement Email;

  @FindBy(id = "tbPassword")
  private WebElement Password;

  //co to jest?
  @FindBy(id = "btnSubmitGoToHome")
  private WebElement GoToHome;

  @FindBy(id = "tSuccessMessage")
  private WebElement SuccessMessage;

  //register button added, user already exists also

  @FindBy(id = "btnRegister")
  private WebElement RegisterButton;

  @FindBy(id = "lLoginName")
  private WebElement LoginNameMessage;

  public void typeInLoginname(String value) {
    typeIn(value, LoginName);
  }

  public void typeInSurname(String value) {
    typeIn(value, Surname);
  }

  public void typeInFirstname(String value) {
    typeIn(value, FirstName);
  }

  public void typeInPatronim(String value) {
    typeIn(value, MiddleName);
  }

  public void typeInEmail(String value) {
    typeIn(value, Email);
  }

  public void typeInPassword(String value) {
    typeIn(value, Password);
  }

  private void typeIn(String value, WebElement targetElement) {
    targetElement.clear();
    targetElement.sendKeys(value);
  }

  public String getMessageText() {
    return SuccessMessage.getText();
  }

  public WebElement getMessageWebElement() {
    return SuccessMessage;
  }

  public String getUserAlreadyExistsMessageText() {
    return LoginNameMessage.getText();
  }

  public WebElement getUserAlreadyExistsMessageWebElement() {
    return LoginNameMessage;
  }

  public void clickRegisterUserButton() {
    RegisterButton.click();
  }
}
