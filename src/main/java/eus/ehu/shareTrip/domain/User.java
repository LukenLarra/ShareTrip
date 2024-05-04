package eus.ehu.shareTrip.domain;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "USERS") // Renames the table to avoid using a reserved keyword
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    private String profileImage;



    //private boolean isDriver;

    public User(String email, String name, String password, String profileImage) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileImage = profileImage;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }


    public int getId() {
        return id.intValue();
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return this.getClass().getSimpleName();
    }
}
