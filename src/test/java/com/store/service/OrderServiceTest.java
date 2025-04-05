package com.store.service;

import com.store.model.dto.OrderRequest;
import com.store.exception.ResourceNotFoundException;
import com.store.model.Order;
import com.store.model.Product;
import com.store.model.Customer;
import com.store.persistence.OrderRepository;
import com.store.persistence.ProductRepository;
import com.store.persistence.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Prueba para la creación de una orden con sus items, mockeando los repositorios de Costumer y Product
 */
@Profile("test")
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerRepository userRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testCreateOrderSuccess() {
        // Configurar DTO de entrada
        OrderRequest request = new OrderRequest();
        request.setCustomerId(1L);
        request.setTotal(BigDecimal.valueOf(350.0));

        OrderRequest.OrderItemRequest item1 = new OrderRequest.OrderItemRequest();
        item1.setProductId(5L);
        item1.setQuantity(2);
        item1.setPrice(BigDecimal.valueOf(100.0));

        OrderRequest.OrderItemRequest item2 = new OrderRequest.OrderItemRequest();
        item2.setProductId(6L);
        item2.setQuantity(1);
        item2.setPrice(BigDecimal.valueOf(150.0));

        request.setItems(List.of(item1, item2));

        // Mocks: se encuentran el user y los productos
        Customer mockUser = new Customer();
        mockUser.setId(1L);
        mockUser.setUserName("user1");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        Product product5 = new Product("Product5", 100.0, 10);
        product5.setId(5L);
        when(productRepository.findById(5L)).thenReturn(Optional.of(product5));

        Product product6 = new Product("Product6", 150.0, 5);
        product6.setId(6L);
        when(productRepository.findById(6L)).thenReturn(Optional.of(product6));

        // Al guardar la orden, retornamos un objeto con ID
        Order mockOrder = new Order();
        mockOrder.setId(100L);
        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);

        // Ejecutar
        Order createdOrder = orderService.createOrder(request);

        assertNotNull(createdOrder);
        assertEquals(100L, createdOrder.getId());
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void testCreateOrderUserNotFound() {
        // Configurar DTO donde userId no existe
        OrderRequest request = new OrderRequest();
        request.setCustomerId(99L);

        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Al intentar crear la orden, esperamos ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> {
            orderService.createOrder(request);
        });
    }
}
