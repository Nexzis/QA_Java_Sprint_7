package praktikum.orders.data;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderChecks {
    @Step("Проверка, что заказ успешно создан ")
        public int orderSuccess(ValidatableResponse orderResponse) {
            int track = orderResponse
                    .assertThat()
                    .statusCode((HttpURLConnection.HTTP_CREATED))
                    .extract()
                    .path("track")
                    ;
            assertNotNull("При успешном логине track не пустой",track);
            assertNotEquals("При успешном логине track не равен 0",0, track);
            return track;
        }

    @Step("Проверка, что выводится список заказов ")
    public static void checkOrderListIsNotEmpty(ValidatableResponse createResponse) {
        ArrayList response = createResponse
                .assertThat()
                .statusCode((HttpURLConnection.HTTP_OK))
                .extract()
                .path("orders")
                ;
        assertNotNull("Полученный ответ null",response);
        assertTrue("Полученный ответ пустой", !response.isEmpty());
    }

}
