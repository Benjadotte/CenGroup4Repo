package net.javaguides.bookstore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, updatable = false)
    private String email;
    private String phoneNumber;
    private String homeAddress;
    private Long creditCardNumber;

    private String password;

    private String role;

    private String confirmPassword;

    @Column(nullable = false, updatable = false)
    private String confirmEmail;

    public User(){}

    public User(String name, String email, String phoneNumber, String homeAddress, Long creditCardNumber, String password, String role, String confirmPassword, String confirmEmail) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.creditCardNumber = creditCardNumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.confirmEmail = confirmEmail;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", creditCardNumber=" + creditCardNumber +
                '}';
    }

    public void setUserCode(String toString) {
    }


    public Collection<Role> getRoles() {
        return null;
    }
}
