package com.store.model.dto;

/**
 * DTO para representar la información de registro de usuario que viene desde React.
 */
public class RegisterRequest {

    private String userName;
    private String password;
    private String email;
    private String role;

    public RegisterRequest() {
    }

    public RegisterRequest(String userName, String password, String email, String role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getters y Setters
    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
