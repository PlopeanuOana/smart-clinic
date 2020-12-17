package com.client.client.DTO;

import com.client.client.DTO.enumeration.UserRole;
import com.client.client.DTO.map.NameIdDTO;

public class UserDTO extends NameIdDTO {
    private String username;
    private String password;
    private String address;
    private String phone;
    private UserRole role;

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return password; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + getId() + '\'' +
                "name='" + getName() + '\'' +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                '}';
    }
}
