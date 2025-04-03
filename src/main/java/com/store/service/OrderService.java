package com.store.service;

import com.store.exception.ResourceNotFoundException;
import com.store.model.*;
import com.store.model.dto.OrderRequest;
import com.store.persistence.CustomerRepository;
import com.store.persistence.OrderRepository;
import com.store.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de Órdenes de compra.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    /**
     * Inyección de dependencias por constructor.
     */
    @Autowired
    public OrderService( OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        ProductRepository productRepository ) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    /**
     * Crea y persiste la orden junto con sus items en la base de datos.
     */
    public Order createOrder(OrderRequest request) {
        //1. Verifica que el usuario existe
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + request.getCustomerId()));

        //2. Crea la orden
        Order order = new Order();
        order.setOrderDate(
                request.getOrderDate() != null ? request.getOrderDate() : LocalDateTime.now()
        );
        order.setStatus( // conversión a tu enum si aplica
                Order.parseStatus(request.getStatus())
        );
        order.setTotal(request.getTotal());
        order.setCustomer(customer);

        // 3. Crear y asociar los items
        List<OrderItem> orderItemList = new ArrayList<>();
        if (request.getItems() != null) {
            request.getItems().forEach(itemReq ->
            {
                Optional<Product> product = productRepository.findById(Long.valueOf(itemReq.getProductId()));
                Product productEntity = product.orElseThrow(() ->
                        new ResourceNotFoundException("Producto no encontrado con ID: " + itemReq.getProductId()));

                OrderItem item = new OrderItem();
                item.setQuantity(itemReq.getQuantity());
                item.setPrice(itemReq.getPrice());
                item.setProduct(productEntity);
                item.setOrder(order);

                orderItemList.add(item);
            });
        }
        order.setOrderItems(orderItemList);

        // 4. Guardar en la BD
        Order savedOrder = orderRepository.save(order);

        return savedOrder;
    }

    /**
     * Retorna la lista de todas las órdenes registradas.
     */
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Busca una orden por su id. Lanza excepción si no la encuentra.
     */
    public Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + id));
    }

    /**
     * Crea o actualiza la orden en la base de datos.
     */
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Elimina una orden dado su id.
     */
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    /**
     * Actualiza el estado de la orden (PENDING, PAID, etc.).
     */
    @Transactional
    public Order updateOrderStatus(Long id, OrderStatus newStatus) {
        Order order = findOrderById(id);
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    /**
     * Retorna todas las órdenes asociadas a un usuario específico.
     */
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }
}

