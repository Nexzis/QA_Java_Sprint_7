package praktikum.courier.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.courier.data.Courier;
import praktikum.courier.data.CourierChecks;
import praktikum.courier.data.CourierClient;
import praktikum.courier.data.LoginDetails;


public class LoginCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    private int courierId;
    private Courier courier;

    @Before
    public void setUp() {
        courier = Courier.random(); // Создание случайного курьера перед каждым тестом
        ValidatableResponse createResponse = client.createCourier(courier);
    }

    @Test
    @DisplayName("Тест логина курьера")
    @Description("Тест проверяет, что логин курьера прошел успешно и вернулось значение его id")
    public void checkIdWhenLogin() {
        var loginDetails = LoginDetails.fromCourier(courier);
        ValidatableResponse loginResponse = client.courierLogin(loginDetails);

        courierId = check.loginSuccess(loginResponse);
    }


    @Test
    @DisplayName("Тест ошибки, когда логин введен неправильно")
    @Description("Тест проверяет, когда логина введен неправильно возвращается 404 и message \"Учетная запись не найдена\"")
    public void checkFailWhenLoginIncorrect() {
        courier.setLogin(courier.getLogin()+"1");

        var loginDetails = LoginDetails.fromCourier(courier);
        ValidatableResponse loginResponse = client.courierLogin(loginDetails);

        check.loginFailed(loginResponse);
    }

    @Test
    @DisplayName("Тест ошибки, когда пароль введен неправильно")
    @Description("Тест проверяет, когда пароль введен неправильно возвращается 404 и message \"Учетная запись не найдена\"")
    public void checkFailWhenPasswordIncorrect() {
        courier.setPassword(courier.getPassword()+"1");

        var loginDetails = LoginDetails.fromCourier(courier);
        ValidatableResponse loginResponse = client.courierLogin(loginDetails);

        check.loginFailed(loginResponse);
    }



    @After
    @DisplayName("Удаление пользователя")
    public void deleteCourier(){
        System.out.println("courierId   " + courierId);
        if (courierId > 0) {
            System.out.println("Удаление пользователя " + courierId);
            client.delete(courierId);
            System.out.println("Удаление пользователя завершено");
        }
    }

}




