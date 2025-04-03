package com.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Representa a un cliente.
 */

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Email", length = 30)
    @NotBlank
    private String email;
    @Column(name = "UserName", length = 10, unique = true)
    @NotBlank
    private String userName;
    @Column(name = "Password", length = 16, nullable = false)
    @NotBlank
    private String password;
    @NotBlank
    @Column(name = "Role", length = 5)
    private String role;
    @Column(name = "Name")
    private String name;
    @Column(name = "LastName")
    private String lastName;

    public Customer(){}

    public Customer(String email, String userName,String password, String role, String name, String lastName){
        this.email = email;
        this.userName = userName;
        this.role = role;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getUserName() {return userName;}
    public void setUserName(String username) {this.userName = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
}