package com.griddynamics.qa.vikta.uitesting.sample.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object of Vikta's Login page
 */
public class LoginPage {

  @FindBy(id = "tfLoginname")
  private WebElement Loginname;

  @FindBy(id = "tfPassword")
  private WebElement Password;

  @FindBy(id = "btnSubmitLogin")
  private WebElement SubmitLogin;

  @FindBy(id = "btnSubmitGoToHome")
  private WebElement GoToHome;

  @FindBy(xpath = "//div[@class='login']//p[contains(@style, 'color: #FF1C19')]")
  private WebElement Error;

  @FindBy(id = "btnSubmitGoToRegistration")
  private WebElement SubmitGoToRegistration;

  public HomePage login(String username, String password) {
    tryLogin(username, password);
    return new HomePage();
  }

  public void tryLogin(String username, String password) {
    Loginname.clear();
    Loginname.sendKeys(username);

    Password.clear();
    Password.sendKeys(password);

    SubmitLogin.click();
  }

  public RegistrationPage gotoRegistration() {
    SubmitGoToRegistration.click();
    return new RegistrationPage();
  }

  public HomePage gotoHome() {
    GoToHome.click();

    return new HomePage();
  }

  public String getErrorMessage() {
    return Error.getText();
  }

  public WebElement getErrorWebElement() {
    return Error;
  }
}
