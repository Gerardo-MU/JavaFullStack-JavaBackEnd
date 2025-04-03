package com.store.service;


import com.store.model.Product;
import com.store.persistence.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestión de Productos.
 */

@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Inyección de dependencias por constructor (recomendado).
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retorna la lista de todos los productos.
     */
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Busca un producto por su id.
     */
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    /**
     * Crea o actualiza un producto en la base de datos.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Elimina un producto dado su id.
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

