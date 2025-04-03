package com.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

/**
 * Representa un item dentro de la Orden.
 */
@Entity
@Table(name = "order_items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    private Integer quantity;

    @PositiveOrZero
    private BigDecimal price;

    /**
     * Producto asociado a este ítem.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProductId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Product product;

    /**
     * Representa un elemento de la orden del cliente.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID")
    @JsonIgnoreProperties({"orderItems", "user"}) // para evitar recursión
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, BigDecimal price, Product product, Order order) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.order = order;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
