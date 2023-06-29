package org.example.clients;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.objects.OrderObject;

import static io.restassured.RestAssured.given;
import static org.example.clients.CourierClient.PATH_ORDER;

public class OrderClient {
    public ValidatableResponse requestGetListOrders(OrderObject order) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(order)
                .when()
                .post(PATH_ORDER)
                .then();
    }

    public void deleteTrack(int trackId) {
        given()
                .contentType(ContentType.JSON)
                .and()
                .delete(PATH_ORDER + trackId)
                .then();
    }
}