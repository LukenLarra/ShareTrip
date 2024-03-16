package eus.ehu.shareTrip.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class User {
    private String email;
    private String name;
    private String password;


    //private boolean isDriver;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User() {
        this.email = "";
        this.name = "";
        this.password = "";
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
//
//    // boolean isDriver() {
//        return isDriver;
//    }
}
