import io.restassured.RestAssured;
import org.junit.BeforeClass;

import static org.example.clients.CourierClient.BASE_URI;
public class TestClass {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URI;
    }
}
