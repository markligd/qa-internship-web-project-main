package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object of Vikta's Login page
 */
public class LoginPage {

  @FindBy(id = "tfLoginname")
  private WebElement loginName;

  @FindBy(id = "tfPassword")
  private WebElement password;

  @FindBy(id = "btnSubmitLogin")
  private WebElement submitLoginButton;

  @FindBy(id = "btnSubmitGoToHome")
  private WebElement goToHomeButton;

  @FindBy(xpath = "//div[@class='login']//p[contains(@style, 'color: #FF1C19')]")
  private WebElement error;

  @FindBy(id = "btnSubmitGoToRegistration")
  private WebElement submitGoToRegistrationButton;

  public HomePage login(String accountUsername, String accountPassword) {
    tryLogin(accountUsername, accountPassword);
    return new HomePage();
  }

  public void tryLogin(String accountUsername, String accountPassword) {
    loginName.clear();
    loginName.sendKeys(accountUsername);

    password.clear();
    password.sendKeys(accountPassword);

    submitLoginButton.click();
  }

  public RegistrationPage gotoRegistration() {
    submitGoToRegistrationButton.click();
    return new RegistrationPage();
  }

  public HomePage gotoHome() {
    goToHomeButton.click();
    return new HomePage();
  }

  public String getErrorMessage() {
    return error.getText();
  }

  public WebElement getErrorWebElement() {
    return error;
  }
}
