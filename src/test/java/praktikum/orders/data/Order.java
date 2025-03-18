package praktikum.orders.data;

import java.util.List;

public class Order {


    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public Order(List<String> color) {
        this.firstName = "Naruto";
        this.lastName = "Uchiha";
        this.address = "Konoha, 142 apt.";
        this.metroStation = 4;
        this.phone = "+7 800 355 35 35";
        this.rentTime = 5;
        this.deliveryDate = "2020-06-06";
        this.comment = "Saske, come back to Konoha";
        this.color = color;
    }

    public List<String> getColor() {
        return color;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getMetroStation() {
        return metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getComment() {
        return comment;
    }
}
