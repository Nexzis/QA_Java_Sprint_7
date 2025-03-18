package praktikum.courier.data;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.EnvConfig;

import java.util.Map;

public class CourierClient {

    @Step("Создание курьера")
    public static ValidatableResponse createCourier(Courier courier) {
        return EnvConfig.getSpec()
                .body(courier)
                .when()
                .post(EnvConfig.API_CREATE_COURIER)
                .then().log().status()
                .log().body();
    }

    @Step("Удаление курьера")
    public ValidatableResponse delete (int id) {
        return EnvConfig.getSpec()
                .body(Map.of("id", id))
                .when()
                .delete(EnvConfig.API_CREATE_COURIER + id)
                .then().log().status();
    }

    @Step("Логин курьером")
    public static ValidatableResponse courierLogin(LoginDetails loginDetails) {
        return EnvConfig.getSpec()
                .body(loginDetails)
                .when()
                .post(EnvConfig.API_LOGIN_COURIER)
                .then().log().status()
                .log().body();
    }

}
