package com.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
public class Product {
    /**
     * Representa un producto de la tienda.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @PositiveOrZero
    private Double price;

    @PositiveOrZero
    private Integer stock;

    public Product() {
    }

    public Product(String name, Double price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters y setters
    public void setId(Long id){ this.id = id; }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() { return price; }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

