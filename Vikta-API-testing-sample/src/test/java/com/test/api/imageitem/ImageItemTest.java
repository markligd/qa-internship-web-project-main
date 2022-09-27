package com.test.api.imageitem;

import com.test.api.dto.CategoryDTO;
import com.test.api.dto.ImageItemDTO;
import com.test.api.execution.BaseTest;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageItemTest extends BaseTest {
    final public String idQueryParam = "id";

    @Test
    public void canFindImageById() {
        List<ImageItemDTO> ImagesIds = given().spec(defaultRequestSpec())
                .when()
                .get(getTestEnvironment().getImageItemListPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .body().jsonPath().get(idQueryParam);

        final long id = Long.parseLong(String.valueOf(ImagesIds.get(0)));

        ImageItemDTO response = given().spec(defaultRequestSpec())
                .when()
                .queryParam(idQueryParam, id)
                .get(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .as(ImageItemDTO.class);

        assertThat(response.getId())
                .as("Id of requested image and id of collected image should be the same")
                .isEqualTo(id);


    }

    @Test
    public void canNotFindImageByWrongId() {
        Response response = given().spec(defaultRequestSpec())
                .when()
                .queryParam(idQueryParam, 0)
                .get(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .response();

        assertThat(response.getStatusCode())
                .as("Method shouldn't be able to find image by id which doesn't exist")
                .isEqualTo(404);

    }

    @Test
    public void canListAllImages() {
        Response response = given()
                .spec(defaultRequestSpec())
                .when()
                .get(getTestEnvironment().getImageItemListPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .response();

        assertThat(response.getStatusCode())
                .as("It should list all images")
                .isEqualTo(200);
    }


    @Test
    public void canPostNewImage() {
        List<String> newList = new ArrayList<>();
        newList.add("New TAG");

        ImageItemDTO newImage = new ImageItemDTO();
        newImage.setPathToImage("https://photos/60");
        newImage.setAuthor("Picasso");
        newImage.setDescription("Planes");
        newImage.setPrice(5.11);
        newImage.setRating(3);
        newImage.setTitle("Picasso planes");
        newImage.setCategoryIds(new HashSet<>());
        newImage.setTags(newList);

        Response response = given().spec(defaultRequestSpec())
                .body(newImage)
                .when()
                .post(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .response();

        assertThat(response.getStatusCode())
                .as("New image should be created")
                .isEqualTo(201);
    }
    @ParameterizedTest
    @MethodSource("NonAcceptableDataForCreatingNewImage")
    public void canNotPostNewImageWithNonAcceptableData(ImageItemDTO newImageItem){
        Response response = given().spec(defaultRequestSpec())
                .body(newImageItem)
                .when()
                .post(getTestEnvironment().getImageItemPath())
                .then()
                .extract()
                .response();

        assertThat(response.getStatusCode())
                .as("New image should not be created")
                .isEqualTo(500);
    }

    @Test
    public void canUpdateImage() {
        List<String> newList = new ArrayList<>();
        newList.add("Tag");

        ImageItemDTO newImage = new ImageItemDTO();
        newImage.setPathToImage("https://photos/60");
        newImage.setAuthor("Picasso");
        newImage.setDescription("Planes");
        newImage.setPrice(5.11);
        newImage.setRating(3);
        newImage.setTitle("Picasso planes");
        newImage.setCategoryIds(new HashSet<>());
        newImage.setTags(newList);

        ImageItemDTO createdImageResponse = given().spec(defaultRequestSpec())
                .body(newImage)
                .when()
                .post(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .as(ImageItemDTO.class);

        newImage.setAuthor("New_Author");
        newImage.setPrice(123.123);
        newImage.setDescription("New_Description");
        newImage.setId(createdImageResponse.getId());

        Response updatedImageResponse = given().spec(defaultRequestSpec())
                .body(newImage)
                .when()
                .put(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .response();

        assertThat(updatedImageResponse.getStatusCode())
                .as("Image should be updated")
                .isEqualTo(200);
    }

    @Test
    public void canNotUpdateNonExistingImage() {
        List<String> newList = new ArrayList<>();
        newList.add("Tag");

        ImageItemDTO newImage = new ImageItemDTO();
        newImage.setPathToImage("https://photos/60");
        newImage.setAuthor("Picasso");
        newImage.setDescription("Planes");
        newImage.setPrice(5.11);
        newImage.setRating(3);
        newImage.setTitle("Picasso planes");
        newImage.setCategoryIds(new HashSet<>());
        newImage.setTags(newList);
        newImage.setId(0L);

        Response imageResponse = given().spec(defaultRequestSpec())
                .body(newImage)
                .when()
                .put(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .response();

        assertThat(imageResponse.getStatusCode())
                .as("Image should not be updated")
                .isEqualTo(404);
    }

    @Test
    public void canDeleteImage() {
        List<String> newList = new ArrayList<>();
        newList.add("Tag");

        ImageItemDTO newImage = new ImageItemDTO();
        newImage.setPathToImage("https://photos/60");
        newImage.setAuthor("Picasso");
        newImage.setDescription("Planes");
        newImage.setPrice(5.11);
        newImage.setRating(3);
        newImage.setTitle("Picasso planes");
        newImage.setCategoryIds(new HashSet<>());
        newImage.setTags(newList);


        ImageItemDTO imageResponse = given().spec(defaultRequestSpec())
                .body(newImage)
                .when()
                .post(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .as(ImageItemDTO.class);

        Long id = imageResponse.getId();

        Response deleteImageResponse = given().spec(defaultRequestSpec())
                .queryParam(idQueryParam, id)
                .when()
                .delete(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .response();

        assertThat(deleteImageResponse.getStatusCode())
                .isEqualTo(200);
    }

    @Test
    public void canNotDeleteNonExistingImage() {
        Response deleteImageResponse = given().spec(defaultRequestSpec())
                .queryParam(idQueryParam, 0)
                .when()
                .delete(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .response();

        assertThat(deleteImageResponse.getStatusCode())
                .isEqualTo(404);


    }

    @Test
    public void canSearchEntity() {
        List<ImageItemDTO> imageList = given()
                .spec(defaultRequestSpec())
                .when()
                .get(getTestEnvironment().getImageItemListPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .body().jsonPath().getList("", ImageItemDTO.class);

        String newImageTerms = imageList.stream()
                .map(ImageItemDTO::getTags)
                .flatMap(images -> images.stream())
                .collect(Collectors.joining("|"));


        List<ImageItemDTO> getEntityResponseList = given()
                .spec(defaultRequestSpec())
                .queryParam("term", newImageTerms)
                .when()
                .get(getTestEnvironment().getImageItemSearchPath())
                .then()
                .extract()
                .body().jsonPath().getList("", ImageItemDTO.class);


        assertThat(imageList.stream()
                .filter(image -> getEntityResponseList.contains(image))
                .findFirst()
                .isPresent()).isTrue();

    }

    @Test
    public void canLinkImageToCategory() {
        Map<String, Long> argumentsMap = new HashMap<>();

        ImageItemDTO newImage = new ImageItemDTO();
        newImage.setPathToImage("https://photos/60");
        newImage.setAuthor("Picasso");
        newImage.setDescription("Planes");
        newImage.setPrice(5);
        newImage.setRating(3);
        newImage.setTitle("Picasso planes");
        newImage.setCategoryIds(new HashSet<>());
        newImage.setTags(new ArrayList<>());

        ImageItemDTO postNewImage = given().spec(defaultRequestSpec())
                .body(newImage)
                .when()
                .post(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract().as(ImageItemDTO.class);

        long itemId = postNewImage.getId();
        long categoryId = getRandomCategoryId();
        System.out.println(categoryId);

        argumentsMap.put("itemId", itemId);
        argumentsMap.put("categoryId", categoryId);

        Response responseLinkedCategory = given().spec(defaultRequestSpec())
                .body(argumentsMap)
                .when()
                .put(getTestEnvironment().getImageItemLinkPath())
                .then()
                .spec(defaultResponseSpec())
                .extract().response();

        assertThat(responseLinkedCategory.getStatusCode()).isEqualTo(200);


    }

    @Test
    public void canUnlinkCategory() {
        Map<String, Long> argumentsMap = new HashMap<>();
        Set<Long> categoriesSet = new HashSet<>();
        long categoryId = getRandomCategoryId();
        categoriesSet.add(categoryId);

        ImageItemDTO newImage = new ImageItemDTO();
        newImage.setPathToImage("https://photos/60");
        newImage.setAuthor("Picasso");
        newImage.setDescription("Planes");
        newImage.setPrice(5);
        newImage.setRating(3);
        newImage.setTitle("Picasso planes");
        newImage.setCategoryIds(categoriesSet);
        newImage.setTags(new ArrayList<>());

        ImageItemDTO postNewImage = given().spec(defaultRequestSpec())
                .body(newImage)
                .when()
                .post(getTestEnvironment().getImageItemPath())
                .then()
                .spec(defaultResponseSpec())
                .extract().as(ImageItemDTO.class);

        long itemId = postNewImage.getId();

        argumentsMap.put("itemId", itemId);
        argumentsMap.put("categoryId", categoryId);

        Response responseUnlinkedCategory = given().spec(defaultRequestSpec())
                .body(argumentsMap)
                .when()
                .put(getTestEnvironment().getImageItemUnlinkPath())
                .then()
                .spec(defaultResponseSpec())
                .extract().response();

        assertThat(responseUnlinkedCategory.getStatusCode()).isEqualTo(200);


    }


    @ParameterizedTest
    @MethodSource("acceptableDataForAdvancedSearch")
    public void canSearchAdvancedItem(double maxPrice, double minPrice, int maxRating, int minRating, List<String> tagsList) {
        List<ImageItemDTO> getEntityResponseList = given()
                .spec(defaultRequestSpec())
                .queryParams("priceMax", maxPrice)
                .queryParams("priceMin", minPrice)
                .queryParams("ratingMax", maxRating)
                .queryParams("ratingMin", minRating)
                .queryParams("tags", tagsList)
                .when()
                .get(getTestEnvironment().getImageItemAdvancedSearchPath())
                .then().log().all()
                .extract()
                .body().jsonPath().getList("", ImageItemDTO.class);

        assertThat(getEntityResponseList).isNotEmpty();

    }
    @ParameterizedTest
    @MethodSource("nonAcceptableDataForAdvancedSearch")
    public void canNotSearchAdvancedItem(double maxPrice, double minPrice, int maxRating, int minRating, List<String> tagsList) {
        List<ImageItemDTO> getEntityResponseList = given()
                .spec(defaultRequestSpec())
                .queryParams("priceMax", maxPrice)
                .queryParams("priceMin", minPrice)
                .queryParams("ratingMax", maxRating)
                .queryParams("ratingMin", minRating)
                .queryParams("tags", tagsList)
                .when()
                .get(getTestEnvironment().getImageItemAdvancedSearchPath())
                .then().log().all()
                .extract()
                .body().jsonPath().getList("", ImageItemDTO.class);

        assertThat(getEntityResponseList).isEmpty();

    }

    private static Stream<Arguments> acceptableDataForAdvancedSearch() {
        List<String> newTagsList = new ArrayList<>();
        newTagsList.add("MO");
        return Stream.of(
                Arguments.of(20.5, 1.25, 5, 0, newTagsList),
                Arguments.of(20.5, 19.34, 5, 5, newTagsList)
        );
    }

    private static Stream<Arguments> nonAcceptableDataForAdvancedSearch() {
        List<String> newTagsList = new ArrayList<>();
        newTagsList.add("MO");
        return Stream.of(
                Arguments.of(19.33, 1.25, 5, 0, newTagsList),
                Arguments.of(20.5, 1.25, 5, 0, new ArrayList<>()),
                Arguments.of(19.33, 1.25, 0, 0, newTagsList)
        );
    }

    private long getRandomCategoryId() {
        List<CategoryDTO> categories = given().spec(defaultRequestSpec())
                .when()
                .get(getTestEnvironment().getCategoriesPath())
                .then()
                .spec(defaultResponseSpec())
                .extract().jsonPath().getList("", CategoryDTO.class);
        return categories.get(new Random().nextInt(categories.size())).getId();
    }

    private static Stream<ImageItemDTO> NonAcceptableDataForCreatingNewImage() {
        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        List<String> tags = new ArrayList<>();
        tags.add("New_tag");
        return Stream.of(
                new ImageItemDTO("picasso", ids, "planes", "", 5.8, 2, tags, "Picasso planes"),
                new ImageItemDTO("", ids, "planes", "https://photos/60", 5.8, 2, tags, "Picasso planes"),
                new ImageItemDTO("picasso", ids, "planes", "https://photos/60", 5.8, 2, tags, "")
        );
    }
}
