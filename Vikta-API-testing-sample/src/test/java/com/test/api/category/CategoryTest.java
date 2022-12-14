package com.test.api.category;

import com.test.api.dto.CategoryDTO;
import com.test.api.execution.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;


public class CategoryTest extends BaseTest {


    @Test
    public void verifyGetCategory() {
        final int categoryId = 5;
        final String expectedTitle = "Street";

        assertThat(getExistingCategoryById(categoryId).getTitle())
                .as("Category title is unexpected")
                .isEqualTo(expectedTitle);
    }


    @Test
    public void verifyGetCategoryWithUnexpectedDescription() {
        final int categoryId = 5;
        final String expectedDescription = "Hammm";

        assertThat(getExistingCategoryById(categoryId).getDescription())
                .as("Category description is unexpected")
                .isEqualTo(expectedDescription);
    }


    @Test
    public void checkCategoryInAllCategoriesList() {
        final int categoryId = 7;
        final CategoryDTO expectedCategory = getExistingCategoryById(categoryId);

        CategoryDTO[] categories = given().spec(defaultRequestSpec())
                .when()
                .get(getTestEnvironment().getCategoriesPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .as(CategoryDTO[].class);


        assertThat(categories)
                .as("Category wasn't found in all categories list")
                .contains(expectedCategory);

    }


    @Test
    public void postNewCategoryWithAcceptableData() {
        HashSet<Long> newSet = new HashSet<>();
        newSet.add(100L);

        HashMap<String, Object> newCategoryMap = new HashMap<>();
        newCategoryMap.put("pathToCatImage", "https://photos/33");
        newCategoryMap.put("title", "Category title");
        newCategoryMap.put("description", "Category description");
        newCategoryMap.put("imageItemIds", newSet);

        Response response = given()
                .spec(defaultRequestSpec())
                .body(newCategoryMap)
                .when()
                .post(getTestEnvironment().getCategoryPath())
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        assertThat(response.getStatusCode()).as("It should post new category").isEqualTo(201);
    }


    @ParameterizedTest
    @MethodSource("NonAcceptableDataForNewCategory")
    public void canNotPostNewCategoryWithNonAcceptableData(long imageItemIds, String pathToCatImage, String title, String description) {
        HashSet<Long> newSet = new HashSet<>();
        newSet.add(imageItemIds);

        HashMap<String, Object> newCategoryMap = new HashMap<>();
        newCategoryMap.put("pathToCatImage", pathToCatImage);
        newCategoryMap.put("title", title);
        newCategoryMap.put("description", description);
        newCategoryMap.put("imageItemIds", newSet);

        Response response = given()
                .spec(defaultRequestSpec())
                .body(newCategoryMap)
                .when()
                .post(getTestEnvironment().getCategoryPath())
                .then()
                .extract()
                .response();

        assertThat(response.getStatusCode()).isEqualTo(500);
    }

    @Test
    public void canPostAndDeleteCategory() {
        final String idQueryParam = "id";
        HashSet<Long> newSet = new HashSet<>();
        newSet.add(541L);

        CategoryDTO categoryAttributes = new CategoryDTO();
        categoryAttributes.setPathToCatImage("https://photos/33");
        categoryAttributes.setTitle("NewImageCategory");
        categoryAttributes.setDescription("NewImage description");
        categoryAttributes.setImageItemIds(newSet);

        Response postResponse = given()
                .spec(defaultRequestSpec())
                .body(categoryAttributes)
                .when()
                .post(getTestEnvironment().getCategoryPath())
                .then()
                .extract()
                .response();

        assertThat(postResponse.getStatusCode()).isEqualTo(201);


        Response deleteCategoryResponse = given()
                .spec(defaultRequestSpec())
                .queryParam(idQueryParam, Integer.parseInt(postResponse.jsonPath().get("id").toString()))
                .when()
                .delete(getTestEnvironment().getCategoryPath())
                .then()
                .extract()
                .response();

        assertThat(deleteCategoryResponse.getStatusCode()).as("Category should be deleted").isEqualTo(200);

    }

    @Test
    public void canNotDeleteCategoryWhichDoesntExist() {
        final String idQueryParam = "id";
        final int id = 0;

        Response deleteImageResponse = given()
                .spec(defaultRequestSpec())
                .queryParam(idQueryParam, id)
                .when()
                .delete(getTestEnvironment().getCategoryPath())
                .then()
                .extract()
                .response();

        assertThat(deleteImageResponse.getStatusCode())
                .as("This method should not be able to find and delete not existing category")
                .isEqualTo(404);

    }

    @Test
    public void updateExistingCategory() {
        String updatedCategoryDescription = "updated Category Description";
        String updatedCategoryTitle = "updated Category Title";
        HashSet<Long> newSet = new HashSet<>();
        newSet.add(541L);

        CategoryDTO categoryAttributes = new CategoryDTO();
        categoryAttributes.setPathToCatImage("https://photos/33");
        categoryAttributes.setTitle("NewImageCategory");
        categoryAttributes.setDescription("NewImage description");
        categoryAttributes.setImageItemIds(newSet);

        CategoryDTO postedCategory = given()
                .spec(defaultRequestSpec())
                .body(categoryAttributes)
                .when()
                .post(getTestEnvironment().getCategoryPath())
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(CategoryDTO.class);

        categoryAttributes.setId(postedCategory.getId());
        categoryAttributes.setTitle(updatedCategoryTitle);
        categoryAttributes.setDescription(updatedCategoryDescription);

        Response updatedCategoryResponse = given()
                .spec(defaultRequestSpec())
                .body(categoryAttributes)
                .when()
                .put(getTestEnvironment().getCategoryPath())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        assertThat(updatedCategoryResponse.jsonPath().get("title").toString())
                .as("Title should be updated")
                .isEqualTo(categoryAttributes.getTitle());

        assertThat(updatedCategoryResponse.jsonPath().get("description").toString())
                .as("Description should be updated")
                .isEqualTo(categoryAttributes.getDescription());

    }
    @Test
    public void canGetEntity(){

        List<CategoryDTO> titles = given()
                .spec(defaultRequestSpec())
                .when()
                .get(getTestEnvironment().getCategoriesPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .body().jsonPath().getList("",CategoryDTO.class);


        String newTitlesTerm = titles.stream()
                .map(CategoryDTO::getTitle)
                .map(title -> title.replace(" ", "~"))
                .collect(Collectors.joining("|"));


        List<String> getEntityResponseList = given()
                .spec(defaultRequestSpec())
                .queryParam("term", newTitlesTerm)
                .when()
                .get(getTestEnvironment().getCategoriesSearchPath())
                .then()
                .extract()
                .body().jsonPath().get("title");

        assertThat(titles.size())
                .as("Number of categories titles should be equal to list of collected titles by get request")
                .isEqualTo(getEntityResponseList.size());

    }


    @Test
    public void checkListingAllCategories() {
        CategoryDTO[] categories = given().spec(defaultRequestSpec())
                .when()
                .get(getTestEnvironment().getCategoriesPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .as(CategoryDTO[].class);

        assertThat(categories)
                .as("List of categories wasn't found")
                .isNotEmpty();
    }

    private CategoryDTO getExistingCategoryById(int id) {
        final String idQueryParam = "id";

        return given().spec(defaultRequestSpec())
                .when().queryParam(idQueryParam, id)
                .get(getTestEnvironment().getCategoryPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .as(CategoryDTO.class);
    }


    private static Stream<Arguments> NonAcceptableDataForNewCategory() {
        return Stream.of(
                Arguments.of(12, "https://photos/33", "", "Description of new category"),
                Arguments.of(12, "", "NewCategory", "Description of new category")
        );
    }
}
