package praktikum.orders.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import praktikum.orders.data.OrderChecks;
import praktikum.orders.data.OrderClient;

public class GetOrdersListTest {
    @Test
    @DisplayName("Тест получения списка заказов")
    @Description("Тест проверяет, что по запросу возвращается список заказов и ответ 200 ОК")
    public void checkIdWhenLogin() {
        ValidatableResponse orderListResponse = OrderClient.getOrdersList();
        OrderChecks.checkOrderListIsNotEmpty(orderListResponse);
    }
}
