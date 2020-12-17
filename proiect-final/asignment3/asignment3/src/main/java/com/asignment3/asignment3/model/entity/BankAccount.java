package com.asignment3.asignment3.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity(name="bank_account")
public class BankAccount {

    @Id
    @GeneratedValue
    private Long id;
    private String account_number;
    private Integer security_number;
    private Double amount;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    public BankAccount() { }

    public BankAccount(@JsonProperty("id") Long id,
                       @JsonProperty("account_number") String account_number,
                       @JsonProperty("security_number") Integer security_number,
                       @JsonProperty("amount") Double amount,
                       @JsonProperty("user_id") Long userId) {
        this.id = id;
        this.account_number = account_number;
        this.security_number = security_number;
        this.amount = amount;
        this.user = new User();
        user.setId(userId);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public void setSecurity_number(Integer security_number) {
        this.security_number = security_number;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public Integer getSecurity_number() {
        return security_number;
    }

    public Double getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }
}
