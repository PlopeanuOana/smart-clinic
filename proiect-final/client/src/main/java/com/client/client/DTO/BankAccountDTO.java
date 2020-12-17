package com.client.client.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccountDTO {
    private Long id;
    private String account_number;
    private Integer security_number;
    private Double amount;
    private UserDTO user;

    public BankAccountDTO() {
    }

    public BankAccountDTO(@JsonProperty("id") Long id,
                          @JsonProperty("account_number") String account_number,
                          @JsonProperty("security_number") Integer security_number,
                          @JsonProperty("amount") Double amount,
                          @JsonProperty("user_id") Long user_id) {
        this.id = id;
        this.account_number = account_number;
        this.security_number = security_number;
        this.amount = amount;
        this.user = new UserDTO();
        user.setId(user_id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public Integer getSecurity_number() {
        return security_number;
    }

    public void setSecurity_number(Integer security_number) {
        this.security_number = security_number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
