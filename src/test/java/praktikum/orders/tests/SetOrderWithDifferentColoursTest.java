package praktikum.orders.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.orders.data.Order;
import praktikum.orders.data.OrderChecks;
import praktikum.orders.data.OrderClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class SetOrderWithDifferentColoursTest {

    private final OrderChecks check = new OrderChecks();
     private final OrderClient client = new OrderClient();
    private final List<String> color;
    private String track;

    public SetOrderWithDifferentColoursTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK", "GREY")},
                {Arrays.asList()} // Пустой список
        });
    }

    @Test
    @DisplayName("Проверка создания заказа с разными вариантами цвета самоката")
    public void checkCreateOrderWithColours() {
        Order order = new Order(color);
        ValidatableResponse orderResponse = OrderClient.createOrder(order);
        check.orderSuccess(orderResponse);
        Integer trackInteger = orderResponse.extract().path("track");
        if (trackInteger != null) {
            track  = trackInteger.toString();
        } else {
            track = null;
        }
    }

    @After
    @DisplayName("Отмена заказа")
    public void cancelOrder(){
        System.out.println("track   " + track);
        if (track != null && !track.isEmpty()) {
            System.out.println("Отмена заказа " + track);
            client.cancelOrder(track);
            System.out.println("Отмена заказа завершена");
        }
    }

}
