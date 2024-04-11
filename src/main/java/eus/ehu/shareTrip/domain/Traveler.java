package eus.ehu.shareTrip.domain;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
@DiscriminatorValue("TRAVELER")
public class Traveler extends User implements Serializable {


    public Traveler(String email, String name, String password) {
        super(email, name, password);
    }

    public Traveler() {
        super();
    }
    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public String toString(){
        return super.toString();
    }



}
