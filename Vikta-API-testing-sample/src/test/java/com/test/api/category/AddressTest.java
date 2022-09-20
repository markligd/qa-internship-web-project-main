package com.test.api.category;

import com.test.api.dto.AddressDTO;
import com.test.api.execution.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AddressTest extends BaseTest {


    @Test
    public void canFindExistingAddressById() {
        final String idQueryParam = "id";
        final Long id = 152L;

        AddressDTO getExistingAddressResponse = given().spec(defaultRequestSpec())
                .when()
                .queryParam(idQueryParam, id)
                .get(getTestEnvironment().getAddressPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .as(AddressDTO.class);

        assertThat(getExistingAddressResponse.getId())
                .as("Id of requested address and id of collected address should be the same")
                .isEqualTo(id);

    }

    @Test
    public void canNotFindAddressByNonExistingId() {
        final String idQueryParam = "id";
        final Long id = 0L;

        Response addressResponse = given().spec(defaultRequestSpec())
                .when()
                .queryParam(idQueryParam, id)
                .get(getTestEnvironment().getAddressPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .response();

        assertThat(addressResponse.getStatusCode()).isEqualTo(404);

    }

    @Test
    public void canPostAndDeleteAddress() {
        AddressDTO addressAttributes = new AddressDTO();
        addressAttributes.setAddressNickname("Highlander");
        addressAttributes.setCityName("Ontario");
        addressAttributes.setPostalCode("35037-200");
        addressAttributes.setRegionName("US");
        addressAttributes.setStreet("Grove");
        addressAttributes.setStreetAdditional("392 82.425019 / -233.10937");


        AddressDTO postResponse = given()
                .spec(defaultRequestSpec())
                .body(addressAttributes)
                .when()
                .post(getTestEnvironment().getAddressPath())
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(AddressDTO.class);

        Long id = postResponse.getId();

        Response deleteAddressResponse = given()
                .spec(defaultRequestSpec())
                .queryParam("id", id)
                .when()
                .delete(getTestEnvironment().getAddressPath())
                .then()
                .extract()
                .response();

        assertThat(deleteAddressResponse.getStatusCode())
                .as("Address should be deleted")
                .isEqualTo(200);

    }

    @Test
    public void canNotDeleteNonExistingAddress() {
        final String idQueryParam = "id";
        final int id = 0;

        Response deleteAddressResponse = given()
                .spec(defaultRequestSpec())
                .queryParam(idQueryParam, id)
                .when()
                .delete(getTestEnvironment().getAddressPath())
                .then()
                .extract()
                .response();

        assertThat(deleteAddressResponse.getStatusCode())
                .as("This method should not be able to find and delete non existing address")
                .isEqualTo(404);
    }


    @Test
    public void canCreateNewAddressWithAcceptableData() {
        AddressDTO addressAttributes = new AddressDTO();
        addressAttributes.setAddressNickname("Highlander");
        addressAttributes.setCityName("Ontario");
        addressAttributes.setPostalCode("35037-200");
        addressAttributes.setRegionName("US");
        addressAttributes.setStreet("Grove");
        addressAttributes.setStreetAdditional("392 82.425019 / -233.10937");


        Response postResponse = given()
                .spec(defaultRequestSpec())
                .body(addressAttributes)
                .when()
                .post(getTestEnvironment().getAddressPath())
                .then()
                .extract()
                .response();
        assertThat(postResponse.getStatusCode()).isEqualTo(201);

    }

    @ParameterizedTest
    @MethodSource("nonAcceptableDataForNewAddress")
    public void canNotCreateNewAddressWithNonAcceptableData(String addressNickname, String cityName, String postalCode, String regionName, String street, String streetAdditional) {
        AddressDTO addressAttributes = new AddressDTO();
        addressAttributes.setAddressNickname(addressNickname);
        addressAttributes.setCityName(cityName);
        addressAttributes.setPostalCode(postalCode);
        addressAttributes.setRegionName(regionName);
        addressAttributes.setStreet(street);
        addressAttributes.setStreetAdditional(streetAdditional);


        Response postResponse = given()
                .spec(defaultRequestSpec())
                .body(addressAttributes)
                .when()
                .post(getTestEnvironment().getAddressPath())
                .then()
                .extract()
                .response();
        assertThat(postResponse.getStatusCode()).isEqualTo(500);


    }

    @Test
    public void canUpdateExistingAddress() {
        String updatedAddressNickname = "Updated Highlander";
        String updatedCityName = "Updated Ontario";

        AddressDTO addressAttributes = new AddressDTO();
        addressAttributes.setAddressNickname("Highlander");
        addressAttributes.setCityName("Ontario");
        addressAttributes.setPostalCode("35037-200");
        addressAttributes.setRegionName("US");
        addressAttributes.setStreet("Grove");
        addressAttributes.setStreetAdditional("392 82.425019 / -233.10937");

        AddressDTO postResponse = given()
                .spec(defaultRequestSpec())
                .body(addressAttributes)
                .when()
                .post(getTestEnvironment().getAddressPath())
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(AddressDTO.class);


        addressAttributes.setAddressNickname(updatedAddressNickname);
        addressAttributes.setCityName(updatedCityName);
        addressAttributes.setId(postResponse.getId());

        Response updatedAddressResponse = given()
                .spec(defaultRequestSpec())
                .body(addressAttributes)
                .when()
                .put(getTestEnvironment().getAddressPath())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(defaultResponseSpec())
                .extract()
                .response();

        assertThat(updatedAddressResponse.jsonPath().get("addressNickname").toString())
                .as("Address nickname should be updated")
                .isEqualTo(addressAttributes.getAddressNickname());

        assertThat(updatedAddressResponse.jsonPath().get("cityName").toString())
                .as("City name should be updated")
                .isEqualTo(addressAttributes.getCityName());


    }

    @Test
    public void canNotUpdateNonExistingAddress() {
        AddressDTO addressAttributes = new AddressDTO();
        addressAttributes.setAddressNickname("Highlander");
        addressAttributes.setCityName("Ontario");
        addressAttributes.setPostalCode("35037-200");
        addressAttributes.setRegionName("US");
        addressAttributes.setStreet("Grove");
        addressAttributes.setStreetAdditional("392 82.425019 / -233.10937");
        addressAttributes.setUserId(0L);

        Response updatedAddressResponse = given()
                .spec(defaultRequestSpec())
                .body(addressAttributes)
                .when()
                .put(getTestEnvironment().getAddressPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .response();

        assertThat(updatedAddressResponse.getStatusCode()).isEqualTo(404);


    }

    @Test
    public void canSearchAddressEntity() {
        List<AddressDTO> addressList = given()
                .spec(defaultRequestSpec())
                .when()
                .get(getTestEnvironment().getAddressesPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .body().jsonPath().getList("", AddressDTO.class);

        String newAddressesTerm = addressList.stream()
                .map(AddressDTO::getAddressNickname)
                .map(address -> address.replace(" ", "~"))
                .collect(Collectors.joining("|"));


        List<String> getEntityResponseList = given()
                .spec(defaultRequestSpec())
                .queryParam("term", newAddressesTerm)
                .when()
                .get(getTestEnvironment().getAddressesSearchPath())
                .then()
                .extract()
                .body().jsonPath().get("addressNickname");


        assertThat(addressList.size())
                .as("Number of addresses nicknames should be equal to list of collected nicknames by get request")
                .isEqualTo(getEntityResponseList.size());
    }


    @Test
    public void canListAllEntities() {
        List<String> addressesList = given().spec(defaultRequestSpec())
                .when()
                .get(getTestEnvironment().getAddressesPath())
                .then()
                .spec(defaultResponseSpec())
                .extract()
                .body().jsonPath().get();

        assertThat(addressesList)
                .as("List of addresses wasn't found")
                .isNotEmpty();
    }


    private static Stream<Arguments> nonAcceptableDataForNewAddress() {
        return Stream.of(
                Arguments.of("Address nickname", "", "32-120", "Mazowieckie", "Krakowska", "Additional info"),
                Arguments.of("Address nickname", "City name", "", "Mazowieckie", "Krakowska", "Additional info"),
                Arguments.of("Address nickname", "City name", "32-120", "", "Krakowska", "Additional info"),
                Arguments.of("Address nickname", "City name", "32-120", "Mazowieckie", "", "Additional info")
        );
    }

}
