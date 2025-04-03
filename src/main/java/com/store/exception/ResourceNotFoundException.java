package com.store.exception;
/**
 * Excepción lanzada si no existe un registro en cualquiera de las bases de datos.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}