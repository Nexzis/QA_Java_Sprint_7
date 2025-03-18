package praktikum.courier.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.courier.data.CourierChecks;
import praktikum.courier.data.CourierClient;
import praktikum.courier.data.LoginDetails;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)

public class LoginCourierWithFieldsTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();

    private final String login;
    private final String password;

    public LoginCourierWithFieldsTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Данные login: {0}, password: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, "dwdawda"},                // Без логина
                {"Userdawfawfa4", null},          // Без пароля
                {null, null},                     // Без логина и пароля
                {"", "dwdawda"},                  // Пустой логин
                {"Userdawfawfa5", ""},            // Пустой пароль
                {"", ""},                         // Пустые логин и пароль
        });
    }

    @Test
    @DisplayName("Проверка, ошибки при незаполненных необходимых полях")
    public void checkFailLoginWithMissedFields() {
        var loginDetails = new LoginDetails(login, password);
        ValidatableResponse loginResponse = client.courierLogin(loginDetails);
        check.failWhenLoginMissedFields(loginResponse);
    }
}




