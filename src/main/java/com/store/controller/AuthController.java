package com.store.controller;

import com.store.model.dto.AuthRequest;
import com.store.model.Customer;
import com.store.model.dto.RegisterRequest;
import com.store.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador básico para la autenticación de usuarios.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomerService customerService;

    /**
     * Inyección de dependencias por constructor.
     */
    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Endpoint de login:
     * Recibe un JSON con username y password desde React,
     * verifica si el cliente existe y si la contraseña coincide.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        // Lllama a UserService para hacer el login
        Customer customer = customerService.loginCustomer(
                authRequest.getUserName(),
                authRequest.getPassword()
        );

        // Responder con un 200 y un JSON de éxito
        Map<String, Object> responseBody = Map.of(
                "status", HttpStatus.OK.value(),
                "message", "Login exitoso",
                "username", authRequest.getUserName()
        );

        return ResponseEntity.ok(responseBody);
    }

    /**
     * Endpoint de registro:
     * Recibe un JSON con username, password, email y status desde React,
     * verifica si el cliente ya está registrado.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        //Llamar a UserService para registrar
        Customer newCustomer = customerService.registerCustomer(
                registerRequest.getUserName(),
                registerRequest.getPassword(),
                registerRequest.getEmail(),
                registerRequest.getRole()
        );

        // Responder con un 200 y un JSON de éxito
        Map<String, Object> responseBody = Map.of(
                "status", HttpStatus.CREATED.value(),
                "message", "Usuario registrado con éxito",
                "username", newCustomer.getUserName()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
