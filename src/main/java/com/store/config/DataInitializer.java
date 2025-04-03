package com.store.config;

import com.store.model.*;
import com.store.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Inicializa datos de prueba: 2 usuarios y 5 productos
 * al arrancar la aplicación.
 */
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            CustomerRepository userRepository,
            ProductRepository productRepository
    ) {
        return args -> {

            // 1. Crear Usuarios
            Customer user1 = new Customer();
            user1.setUserName("user1");
            user1.setPassword("pwd1");    // En un proyecto real, usar encriptación
            user1.setEmail("user1@test.com");
            user1.setRole("USER");
            user1.setName("Juan");
            user1.setLastName("Juan Pérez");
            userRepository.save(user1);

            Customer user2 = new Customer();
            user2.setUserName("admin");
            user2.setPassword("admin123");
            user2.setEmail("admin@test.com");
            user2.setRole("ADMIN");
            user2.setName("María");
            user2.setLastName("María González");
            userRepository.save(user2);

            // 2. Crear 5 Productos
            Product productA = new Product("Playera Star Wars", 200.0, 20);
            Product productB = new Product("Funkopop Darth Vader", 500.0, 8);
            Product productC = new Product("Lego X-Wing", 1200.0, 5);
            Product productD = new Product("Gorra Stormtrooper", 300.0, 15);
            Product productE = new Product("Taza Mandalorian", 150.0, 30);

            productRepository.save(productA);
            productRepository.save(productB);
            productRepository.save(productC);
            productRepository.save(productD);
            productRepository.save(productE);

        };
    }
}
