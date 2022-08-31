package com.griddynamics.qa.vikta.uitesting.sample.tests;

import com.griddynamics.qa.vikta.uitesting.sample.auxiliary.DriverManager;
import com.griddynamics.qa.vikta.uitesting.sample.config.DataProvider;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.AddressSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.HomePageSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.LoginSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.RegistrationSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

  // TODO: Think about some IoC/DI here.
  private DriverManager driverManager;

  LoginSteps loginSteps;
  RegistrationSteps registrationSteps;
  HomePageSteps homePageSteps;

  AddressSteps addressSteps;

  BaseTest() {
    driverManager = new DriverManager(DataProvider.get());
  }

  @BeforeClass
  void setupClass() {
    driverManager.instantiateDriver();

    loginSteps = new LoginSteps(driverManager.get());
    registrationSteps = new RegistrationSteps(driverManager.get());
    homePageSteps = new HomePageSteps(driverManager.get());
    addressSteps = new AddressSteps(driverManager.get());
  }

  @AfterClass
  void tearDownClass() {
    driverManager.quite();
  }
}
