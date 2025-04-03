package com.store.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para recibir la información de la Orden y sus items
 * en una sola petición JSON.
 */
public class OrderRequest {

    private LocalDateTime orderDate;
    private String status;
    private BigDecimal total;
    private long customerId;
    private List<OrderItemRequest> items;

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) {  this.status = status; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total;}
    public Long getCustomerId() { return Long.valueOf(customerId); }

    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }

    // Getters y setters

    public static class OrderItemRequest {
        private Integer quantity;
        private BigDecimal price;
        private long productId;

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }
        public Long getProductId() {return Long.valueOf(productId); }
    }
}

