package com.back_end_project.back_end_project.controllerComponentJWT.model;

import com.back_end_project.back_end_project.database.Customer;

public class LoginResponse {

    private String message; // 訊息
    private String token; // JWT Token
    private Customer customer; // 客戶資訊

    public LoginResponse(String message) {
        this.message = message;
    }

    public LoginResponse(String message, String token, Customer customer) {
        this.message = message;
        this.token = token;
        this.customer = customer;
    }

    // Getters 和 Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
