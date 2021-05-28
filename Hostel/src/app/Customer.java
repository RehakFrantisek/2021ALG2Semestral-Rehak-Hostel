package app;

public class Customer implements User {

    private String username;
    private String password;
    protected String name;
    protected String surname;
    protected int phone;

    public Customer(String username, String password, String name, String surname, int phone){
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone  = phone;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + name + "," + surname + "," + phone;
    }

    @Override
    public void saveUsersToFile() {
    }

    @Override
    public String getUsername() {
        return username;
    }
}