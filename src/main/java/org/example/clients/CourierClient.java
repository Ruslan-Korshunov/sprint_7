package org.example.clients;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.data.CourierData;
import org.example.objects.CourierObject;

import static io.restassured.RestAssured.given;

public class CourierClient {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    private static final String PATH = "api/v1/courier";
    private static final String LOGIN_PATH = "api/v1/courier/login";
    public static final String PATH_ORDER = "/api/v1/orders";
    private static final String API_DELETE = "/api/v1/courier/";
    public CourierClient() {
        RestAssured.baseURI = BASE_URI;
    }
    @Step("Создание курьера")
    public ValidatableResponse requestCreateCourier(CourierObject courier) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(courier)
                .when()
                .post(PATH)
                .then();
    }

    @Step("Создание логин курьера в системе")
    public ValidatableResponse requestCreateLogin(CourierData data) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(data)
                .when()
                .post(LOGIN_PATH)
                .then();
    }
    @Step("Создание логин курьера в системе без пароль")
    public ValidatableResponse requestCreateLoginInSystem(CourierObject courier) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(courier)
                .when()
                .post(LOGIN_PATH)
                .then();
    }


    @Step("Удаление id курьера")
    public void courierDelete(String courierId) {
        given()
                .contentType(ContentType.JSON)
                .delete(API_DELETE + courierId)
                .then();
    }
}
