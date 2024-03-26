package eus.ehu.shareTrip.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class Traveler extends User implements Serializable {

    @Id
    private String email;
    private String name;
    private String password;


    public Traveler(String email, String name, String password) {
        super(email, name, password);
    }

    public Traveler() {
        super();
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
