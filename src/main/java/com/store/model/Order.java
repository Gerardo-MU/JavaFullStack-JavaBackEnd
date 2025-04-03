package com.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una Orden de compra.
 */
@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "orderItems"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @PositiveOrZero
    private BigDecimal total;

    /**
     * Usuario que realizó la orden.
     * Relación ManyToOne: un usuario -> muchas órdenes.
     * (Suponiendo que ya tienes una clase User con @Entity)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserName")
    private Customer customer;

    /**
     * Lista de items en esta orden.
     * Una orden puede tener varios items.
     * 'mappedBy' se refiere al campo 'order' en la clase OrderItem.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"order"}) // Para evitar recursión con OrderItem
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(LocalDateTime orderDate, OrderStatus status, BigDecimal total, Customer customer) {
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
        this.customer = customer;
    }

    /**
     * Método estático para convertir un String a un OrderStatus.
     */
    public static OrderStatus parseStatus(String statusString) {
        if (statusString == null) {
            return OrderStatus.PENDING;
        }
        return OrderStatus.valueOf(statusString.toUpperCase());
    }

    // Getters y setters
    public Long getId() {
        return id;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
