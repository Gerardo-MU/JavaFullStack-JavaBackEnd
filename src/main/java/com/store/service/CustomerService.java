package com.store.service;
import com.store.exception.InvalidCredentialsException;
import com.store.exception.ResourceNotFoundException;
import com.store.exception.UserAlreadyExistsException;
import com.store.model.Customer;
import com.store.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de usuarios.
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Inyección de dependencias por constructor.
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Retorna la lista de todos los clientes registrados.
     */
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    /**
     * Busca un clientes por su id. Lanza una excepción o regresa nulo si no se encuentra.
     */
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> {throw new ResourceNotFoundException("Producto no encontrado con ID " + id);});
    }

    /**
     * Crea o actualiza un usuario en la base de datos.
     */
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Busca un cliente por su nombre de usuario registrado
     */
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUserName(username);
    }

    public Customer loginCustomer(String username, String password) {
        //Realiza la consulta de la existencia del cliente
        Optional<Customer> existingCustomer = customerRepository.findByUserName(username);

        //Revisa si existe el UserID
        if (!existingCustomer.isPresent()) {
            throw new InvalidCredentialsException("Username " + username + " no existe. Intente de nuevo");
        }

        //Revisa si las contraseñas coinciden
        if (!existingCustomer.get().getPassword().equals(password)) {
            throw new InvalidCredentialsException("La contraseña no coincide.");
        }
        return existingCustomer.get();
    }

    /**
     * Registra un nuevo usuario. Lanza excepción si username o email ya existen.
     */
    public Customer registerCustomer(String username, String password, String email, String role) {
        // 1. Verificar si ya existe un usuario con ese username
        Optional<Customer> existingUser = customerRepository.findByUserName(username);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Username " + username + " ya está en uso.");
        }

        // 2. Crear la instancia de User
        Customer newCustomer = new Customer();
        newCustomer.setUserName(username);
        newCustomer.setPassword(password); // En producción, encripta la contraseña (BCrypt)
        newCustomer.setEmail(email);
        newCustomer.setRole(role);
        newCustomer.setName("-");
        newCustomer.setLastName("-");

        // 3. Guardar en BD
        return customerRepository.save(newCustomer);
    }

    /**
     * Elimina un cliente por su Id.
     */
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}