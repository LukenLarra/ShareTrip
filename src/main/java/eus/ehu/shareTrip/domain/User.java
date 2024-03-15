package eus.ehu.shareTrip.domain;

public class User {
    private String email;
    private String name;
    private String password;
    private boolean isDriver;

    public User(String email, String name, String password, boolean isDriver) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.isDriver = isDriver;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isDriver() {
        return isDriver;
    }
}
