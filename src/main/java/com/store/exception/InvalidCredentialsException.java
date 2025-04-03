package com.store.exception;


/**
 * Excepción para credenciales inválidas (login fallido).
 */
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}