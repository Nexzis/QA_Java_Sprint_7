package praktikum;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class EnvConfig {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    public static final String API_CREATE_COURIER = "/api/v1/courier/";
    public static final String API_LOGIN_COURIER = "/api/v1/courier/login";
    public static final String API_CANCEL_ORDER_BY_TRACK = "/api/v1/orders/cancel?track=";
    public static final String API_CREATE_ORDER = "/api/v1/orders/";


    @Step("Передача BASE_URI")
    public static RequestSpecification getSpec() {
        return given()
                .log().method()
                .log().uri()
                .log().body()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }


}
