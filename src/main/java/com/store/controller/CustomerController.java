package com.store.controller;

import com.store.model.Customer;
import com.store.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    /**
     * Inyección de dependencias por constructor.
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }
}