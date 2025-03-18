package praktikum.courier.data;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.net.HttpURLConnection;

import static org.junit.Assert.*;

public class CourierChecks {
    @Step("Проверка, что курьер создан")
    public void created(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode((HttpURLConnection.HTTP_CREATED))
                .extract()
                .path("ok")
                ;
        assertTrue("Ожидался статус HTTP_CREATED, но ответ не соответствует",created) ;
    }

    @Step("Проверка, что ошибка если создавать уже существующего курьера")
    public void conflictWhenCourierAlreadyCreated(ValidatableResponse createResponse) {
        String response = createResponse
                .assertThat()
                .statusCode((HttpURLConnection.HTTP_CONFLICT))
                .extract()
                .path("message")
                ;
        assertEquals("Полученный ответ "+ response,"Этот логин уже используется", response); ;
    }

    @Step("Проверка, что ошибка если при создании не передаются необходимые данные ")
    public void failWhenCreateMissedFields(ValidatableResponse createResponse) {
        String response = createResponse
                .assertThat()
                .statusCode((HttpURLConnection.HTTP_BAD_REQUEST))
                .extract()
                .path("message")
                ;
        assertEquals("Полученный ответ "+ response,"Недостаточно данных для создания учетной записи", response);
    }

    @Step("Проверка, что логин прошел успешно ")
        public int loginSuccess(ValidatableResponse loginResponse) {
            int id = loginResponse
                    .assertThat()
                    .statusCode((HttpURLConnection.HTTP_OK))
                    .extract()
                    .path("id")
                    ;
            assertNotNull("При успешном логине id не пустой",id);
            assertNotEquals("При успешном логине id не равен 0",0, id);
            return id;
        }

    @Step("Проверка, что ошибка если при логине не передаются необходимые данные ")
    public void failWhenLoginMissedFields(ValidatableResponse createResponse) {
        String response = createResponse
                .assertThat()
                .statusCode((HttpURLConnection.HTTP_BAD_REQUEST))
                .extract()
                .path("message")
                ;
        assertEquals("Ожидается ответ "+ response,"Недостаточно данных для входа", response);
    }

    @Step("Проверка, что ошибка если логинится несуществующий курьер ")
    public void loginFailed(ValidatableResponse loginResponse) {
        String response = loginResponse
                .assertThat()
                .statusCode((HttpURLConnection.HTTP_NOT_FOUND))
                .extract()
                .path("message")
                ;
        Assert.assertEquals("Ожидается ответ  " + response, "Учетная запись не найдена", response);
    }

}
