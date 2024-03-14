package eus.ehu.shareTrip.domain;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Traveler implements Serializable {

    @Id
    private String email;
    private String name;

    public Traveler() {
        super();
    }

    public Traveler(String email, String name) {
        this.email = email;
        this.name = name;
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

    public String toString(){
        return email+";"+name;
    }

    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Traveler)) return false;
        Traveler t = (Traveler)obj;
        return t.getEmail().equals(this.getEmail());
    }

    public int hashCode(){
        return email.hashCode();
    }



}
