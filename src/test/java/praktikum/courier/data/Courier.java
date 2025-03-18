package praktikum.courier.data;

import io.qameta.allure.Step;

import java.util.concurrent.ThreadLocalRandom;

public class Courier {
    private String login;
    private String password;
    private final String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Step("Генерация login, password, firstName для будущего курьера ")
    public static Courier random(){
        int suffix = ThreadLocalRandom.current().nextInt(100,10000);
        return new Courier("User"+ suffix, "Qwerty!"+ suffix, "maName");
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }
    public String setLogin(String login) {
        this.login = login;
        return login;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }
}
