package app;


public class Admin implements User {

    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static void customerList() {

    }

    public static void removeRoom() {

    }


    @Override
    public void saveUsersToFile() {

    }

    @Override
    public String getUsername() {
        return username + "," + password;
    }
}
