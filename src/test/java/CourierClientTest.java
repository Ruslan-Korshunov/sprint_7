import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.example.objects.CourierObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.example.clients.CourierClient;

import static org.example.data.CourierData.getCourierData;
import static org.example.generator.CourierGenerator.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierClientTest {
    private CourierClient createCourier;
    private CourierObject validCourier;
    private CourierClient сourierWithoutPassword;
    private CourierObject emptyPasswordCourierObject;
    private CourierClient loginCourierClient;
    private CourierObject loginCourierObject;
    private CourierClient loginCourierWithoutPassword;
    private CourierObject loginEmptyPasswordCourierObject;
    private CourierClient createCourierNoPassword;
    private CourierObject emptyPassword;
    private CourierObject noPasswordCourierObject;
    private String courierId;
    @Before
    public void setUp() {
        createCourier = new CourierClient();
        validCourier = generateCourier();
        сourierWithoutPassword = new CourierClient();
        emptyPasswordCourierObject = generateCourierEmptyPassword();
        loginCourierClient = new CourierClient();
        loginCourierObject = generateCourierOnlyLoginPassword();
        loginCourierWithoutPassword = new CourierClient();
        loginEmptyPasswordCourierObject = generateCourierOnlyLoginEmptyPassword();
        createCourierNoPassword = new CourierClient();
        emptyPassword = generateCourierEmptyPassword();
        noPasswordCourierObject = generateCourierNoPassword();
    }
    @Test
    @DisplayName("Создание курьера: эндпоинт создания api/v1/courier")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void createCourier() {
        ValidatableResponse response = createCourier.requestCreateCourier(validCourier);
        response.assertThat().statusCode(HttpStatus.SC_CREATED).body("ok", is(true));
    }
    @Test
    @DisplayName("Логин курьера в системе: эндпоинт api/v1/courier/login")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void requestLoginCourierInSystem() {
        createCourier.requestCreateCourier(validCourier);
        ValidatableResponse loginResponse = createCourier.requestCreateLogin(getCourierData(validCourier));
        courierId = loginResponse.extract().path("id").toString();
        loginResponse.assertThat().statusCode(HttpStatus.SC_OK).body("id", notNullValue());
    }

    @Test
    @DisplayName("Создание курьера с повторяющимся логином: эндпоинт api/v1/courier")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void requestCreateCourierWithRepetitiveLogin() {
        createCourier.requestCreateCourier(validCourier);
        ValidatableResponse createResponse = createCourier.requestCreateCourier(validCourier);
        createResponse.assertThat().statusCode(HttpStatus.SC_CONFLICT).body("message",
                is("Этот логин уже используется. Попробуйте другой."));

    }
    @Test
    @DisplayName("Создание курьера без пароля: эндпоинт логина api/v1/courier")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void requestWithoutPasswordTest(){
        ValidatableResponse loginResponse = сourierWithoutPassword.requestCreateCourier(emptyPasswordCourierObject);
        loginResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).body("message",
                is("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Создание курьера без поля пароль: эндпоинт логина api/v1/courier")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void requestWithoutFieldPasswordTest(){
        ValidatableResponse loginResponse = createCourierNoPassword.requestCreateCourier(emptyPassword);
        loginResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).body("message",
                is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание логин курьера в системе, с не существующей парой логин/пароль: эндпоинт логина api/v1/courier/login")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void requestCreateLoginWithNonExistentLoginPasswordTest() {
        ValidatableResponse loginResponse = loginCourierClient.requestCreateLoginInSystem(loginCourierObject);
        loginResponse.assertThat().statusCode(HttpStatus.SC_NOT_FOUND).body("message",
                is("Учетная запись не найдена"));
    }
    @Test
    @DisplayName("Создание логин курьера в системе, без логин или пароля: эндпоинт логина api/v1/courier/login")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void requestCreateLoginWithoutPasswordTest(){
        ValidatableResponse loginResponse = loginCourierWithoutPassword.requestCreateLoginInSystem(loginEmptyPasswordCourierObject);
        loginResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).body("message",
                is("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Создание логин курьера в системе, без поля пароль: эндпоинт логина api/v1/courier/login")
    @Description("Проверка ожидаемого результата: statusCode")
    public void requestCreateLoginWithoutFieldPasswordTest(){
        ValidatableResponse loginResponse = loginCourierClient.requestCreateLoginInSystem(noPasswordCourierObject);
        loginResponse.assertThat().statusCode(HttpStatus.SC_GATEWAY_TIMEOUT);
    }
    @After
    public void tearDown()
    {
        createCourier.courierDelete(courierId);
    }
}