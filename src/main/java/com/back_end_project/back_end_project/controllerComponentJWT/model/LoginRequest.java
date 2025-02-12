package com.back_end_project.back_end_project.controllerComponentJWT.model;

public class LoginRequest {

    private String email; // 登入的 Email
    private String password; // 登入的密碼

    // Getters 和 Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
