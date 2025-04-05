package com.store.service;

import com.store.model.Product;
import com.store.persistence.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Prueba para verificar la creación de un producto y la búsqueda por ID mockeando el ProductRepository
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;  // Clase a testear

    @Test
    void testCreateProduct() {
        Product product = new Product("Nuevo Producto", 100.0, 10);

        // Simulamos que el repositorio guarda y devuelve el mismo objeto
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product created = productService.saveProduct(product);

        assertNotNull(created);
        assertEquals("Nuevo Producto", created.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testFindProductById() {
        Product product = new Product("Funkopop", 250.0, 5);
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product found = productService.findProductById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
        assertEquals("Funkopop", found.getName());
    }
}
