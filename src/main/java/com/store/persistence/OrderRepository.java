package com.store.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import com.store.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Devuelve todas las órdenes que pertenecen a un usuario con el ID especificado.
     */
    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId")
    List<Order> findOrdersByUserId(@Param("customerId") Long customerId);
}

