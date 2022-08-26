package com.griddynamics.qa.vikta.uitesting.sample.tests;

import com.griddynamics.qa.vikta.uitesting.sample.utils.ApiMethods;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

  @Test
  public void findFirstImageByTitle() {
    homePageSteps.openHomePage();

    String firstTitle = ApiMethods.getListOfTitlesOfAvailableItems().get(0);

    homePageSteps.typeItemTitleFromApiToSearchBar(firstTitle);

    homePageSteps.clickSearchButton();

    homePageSteps.checkIfSearchedProductTitleIsInResultsPage(firstTitle);
  }

  @Test
  public void findSecondImageByTitle() {
    homePageSteps.openHomePage();

    String secondTitle = ApiMethods.getListOfTitlesOfAvailableItems().get(1);

    homePageSteps.typeItemTitleFromApiToSearchBar(secondTitle);

    homePageSteps.clickSearchButton();

    homePageSteps.checkIfSearchedProductTitleIsInResultsPage(secondTitle);
  }

  @Test
  public void findFifthImageByTitle() {
    homePageSteps.openHomePage();

    String fifthTitle = ApiMethods.getListOfTitlesOfAvailableItems().get(4);

    homePageSteps.typeItemTitleFromApiToSearchBar(fifthTitle);

    homePageSteps.clickSearchButton();

    homePageSteps.checkIfSearchedProductTitleIsInResultsPage(fifthTitle);
  }
}
