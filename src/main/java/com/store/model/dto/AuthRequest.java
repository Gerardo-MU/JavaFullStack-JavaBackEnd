package com.store.model.dto;

/**
 * DTO para representar la petición de login del usuario en JSON
 */

public class AuthRequest {
    private String userName;
    private String password;

    public AuthRequest(){};

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
