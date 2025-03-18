package praktikum.courier.tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.courier.data.Courier;
import praktikum.courier.data.CourierChecks;
import praktikum.courier.data.CourierClient;
import praktikum.courier.data.LoginDetails;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)

public class CreatingCourierWithFieldsTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    private int courierId;
    private Courier courier;

    private final String login;
    private final String password;
    private final String firstName;

    public CreatingCourierWithFieldsTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters(name = "Данные login: {0}, password: {1}, name: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, "dwdawda", "maName"},                // Без логина
                {"Userdawfawfa4", null, "maName"},          // Без пароля
                {null, null, "maName"},                     // Без логина и пароля
                {"", "dwdawda", "maName"},                  // Пустой логин
                {"Userdawfawfa5", "", "maName"},            // Пустой пароль
                {"", "", "maName"},                         // Пустые логин и пароль
        });
    }

    @Test
    @DisplayName("Проверка, что появляется ошибка, когда при создании курьера не передаются все необходимые поля")
    public void checkFailCreatingWithMissedFields() {
        courier = new Courier(login, password, firstName);
        ValidatableResponse createResponse = client.createCourier(courier);
        check.failWhenCreateMissedFields(createResponse);
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




