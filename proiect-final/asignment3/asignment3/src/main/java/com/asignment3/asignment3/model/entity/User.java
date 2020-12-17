package com.asignment3.asignment3.model.entity;
import com.asignment3.asignment3.model.enumeration.UserRole;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;


@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue
    private  Long id;

    private  String name;

    @Length(min = 10)
    private  String phone;
    private  String address;
    private  String username;

    @Length(min = 6)
    private  String password;

    @Enumerated(value = EnumType.STRING)
    private  UserRole role;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
