package praktikum.courier.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.courier.data.Courier;
import praktikum.courier.data.CourierChecks;
import praktikum.courier.data.CourierClient;
import praktikum.courier.data.LoginDetails;


public class CreatingCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    private int courierId;
    private Courier courier;

    @Before
    public void setUp() {
        courier = Courier.random(); // Создание случайного курьера перед каждым тестом
    }

    @Test
    @DisplayName("Тест создания курьера")
    @Description("Тест проверяет, что создание курьера прошло успешно и вернулся ответ 200 ОК")
    public void checkCreateCourier() {
        ValidatableResponse createResponse = client.createCourier(courier);
        check.created(createResponse);
    }

    @Test
    @DisplayName("Тест создания существующего курьера")
    @Description("Тест проверяет, что создание курьера по данным уже созданного курьера возвращает ошибку 409 и message \"Этот логин уже используется\"")
    public void checkExistedCourier() {
        ValidatableResponse createResponse = client.createCourier(courier);
        ValidatableResponse createResponseAgain = client.createCourier(courier);
        check.conflictWhenCourierAlreadyCreated(createResponseAgain);
    }

    @After
    @DisplayName("Удаление курьера")
    public void deleteCourier(){
        System.out.println("courierId   " + courierId);
        checkLoginAndPasswordExist();
        if (courierId > 0) {
            System.out.println("Удаление пользователя " + courierId);
            client.delete(courierId);
            System.out.println("Удаление пользователя завершено");
        }
    }

    @Step("Проверка что логин и пароль не пустые и получение courierId ")
    public void checkLoginAndPasswordExist() {
        if (courierId == 0 ) {
            if (courier.getLogin() != null && !courier.getLogin().isEmpty() &&
                    courier.getPassword() != null && !courier.getPassword().isEmpty()) {
                System.out.println("Получим courierId   ");
                var loginDetails = LoginDetails.fromCourier(courier);
                ValidatableResponse loginResponse = client.courierLogin(loginDetails);
                courierId = check.loginSuccess(loginResponse);
                System.out.println("Полученный courierId " + courierId);
            }
        }
    }

}




