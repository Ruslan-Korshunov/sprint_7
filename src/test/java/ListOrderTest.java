import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.example.clients.CourierClient.BASE_URI;
import static org.example.clients.CourierClient.PATH_ORDER;


public class ListOrderTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }
    @Test
    @DisplayName("Получение списка заказов: эндпоинт /api/v1/orders")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void getListOrdersTest() {
        Response response = given()
                .get(PATH_ORDER);
        response .then().assertThat().body("$", Matchers.allOf(
                        Matchers.hasKey("orders"),
                        Matchers.hasKey("pageInfo"),
                        Matchers.hasKey("availableStations")))
                .statusCode(HttpStatus.SC_OK);
    }
}
