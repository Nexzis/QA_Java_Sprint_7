package praktikum.orders.data;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.EnvConfig;



public class OrderClient {

    @Step("Создание заказа")
    public static ValidatableResponse createOrder(Order order) {
        return EnvConfig.getSpec()
                .body(order)
                .when()
                .post(EnvConfig.API_CREATE_ORDER)
                .then().log().status()
                .log().body();
    }

        @Step("Отмена заказа")
        public static ValidatableResponse cancelOrder(String track) {
            return EnvConfig.getSpec()
                    .when()
                    .put(EnvConfig.API_CANCEL_ORDER_BY_TRACK + track)
                    .then().log().status()
                    .log().body();
        }


    @Step("Получение списка заказов")
    public static ValidatableResponse getOrdersList() {
        return EnvConfig.getSpec()
                .when()
                .get(EnvConfig.API_CREATE_ORDER)
                .then().log().status()
                .log().body()
                ;
    }



}
