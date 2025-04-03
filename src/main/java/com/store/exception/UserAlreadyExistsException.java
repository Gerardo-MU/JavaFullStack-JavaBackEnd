package com.store.exception;

/**
 * Excepción lanzada cuando se detecta que el usuario o email ya existe.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
