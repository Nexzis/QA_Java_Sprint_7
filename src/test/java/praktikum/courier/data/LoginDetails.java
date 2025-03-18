package praktikum.courier.data;

public class LoginDetails {
    private final String login;
    private final String password;

    public LoginDetails(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static LoginDetails fromCourier(Courier courier){
        return new LoginDetails(courier.getLogin(), courier.getPassword());
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
