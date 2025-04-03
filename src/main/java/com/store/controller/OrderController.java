package com.store.controller;

import com.store.model.Order;
import com.store.model.OrderStatus;
import com.store.model.dto.OrderRequest;
import com.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para órdenes.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * Inyección de dependencias por constructor.
     */
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * GET
     * Retorna la lista de todas las órdenes.
     */
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAllOrders();
    }

    /**
     * GET
     * Retorna una orden por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.findOrderById(id);
        return ResponseEntity.ok(order);
    }

    /**
     * PUT
     * Actualiza una orden existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        orderService.findOrderById(id); // lanza excepción si no se encuentra
        order.setId(id);
        Order updatedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(updatedOrder);
    }

    /**
     * DELETE
     * Elimina una orden por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * PATCH
     * Actualiza el estado de la orden (OrderStatus).
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatus newStatus) {
        Order updated = orderService.updateOrderStatus(id, newStatus);
        return ResponseEntity.ok(updated);
    }


    /**
     * GET
     * Retorna en JSON la lista de órdenes del usuario, cada orden
     * incluyendo sus OrderItems y Products, dependiendo de la configuración de mapeos.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);

        // Si no hay ordenes retorna 204 (No Content):
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // De lo contrario, 200 (OK)
        return ResponseEntity.ok(orders);
    }

    /**
     * POST
     * Recibe un JSON con la estructura de la Order y la registra en BD.
     * Responde con un JSON {status: 200, message: "...", orderId: ...}
     */
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        Order savedOrder = orderService.createOrder(orderRequest);

        // Retorna un objeto JSON con status y ID de la orden
        Map<String, Object> response = Map.of(
                "status", HttpStatus.OK.value(),
                "message", "Orden registrada exitosamente",
                "orderId", savedOrder.getId()
        );

        return ResponseEntity.ok(response);
    }
}
