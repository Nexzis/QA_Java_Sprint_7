package praktikum.courier.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import praktikum.courier.data.Courier;
import praktikum.courier.data.CourierChecks;
import praktikum.courier.data.CourierClient;
import praktikum.courier.data.LoginDetails;


public class LoginCourierNotExistTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    private Courier courier;


    @Test
    @DisplayName("Тест ошибки, когда логина не существует")
    @Description("Тест проверяет, когда логина не существует возвращается 404 и message \"Учетная запись не найдена\"")
    public void checkFailWhenLoginNotExist() {
        courier = Courier.random();
        var loginDetails = LoginDetails.fromCourier(courier);
        ValidatableResponse loginResponse = client.courierLogin(loginDetails);

        check.loginFailed(loginResponse);
    }

}




