package com.griddynamics.qa.vikta.uitesting.sample.tests;

import com.griddynamics.qa.vikta.uitesting.sample.ViktaUITestsApplication;
import com.griddynamics.qa.vikta.uitesting.sample.auxiliary.DriverManager;
import com.griddynamics.qa.vikta.uitesting.sample.config.DataProvider;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.AddressSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.CardSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.HomePageSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.LoginSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.RegistrationSteps;
import com.griddynamics.qa.vikta.uitesting.sample.stepsDefinitions.ShoppingCartSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@SpringBootTest(classes = ViktaUITestsApplication.class)
public class BaseTest {

  @Autowired
  protected LoginSteps loginSteps;
  @Autowired
  protected RegistrationSteps registrationSteps;
  @Autowired
  protected HomePageSteps homePageSteps;
  @Autowired
  protected AddressSteps addressSteps;
  @Autowired
  protected CardSteps cardSteps;
  @Autowired
  protected ShoppingCartSteps shoppingCartSteps;
  @Autowired
  private DriverManager driverManager;

  @AfterClass
  void tearDownClass() {
    driverManager.quite();
  }
}
