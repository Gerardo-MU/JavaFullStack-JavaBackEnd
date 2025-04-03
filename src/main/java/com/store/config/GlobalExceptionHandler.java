package com.store.config;


import com.store.exception.InvalidCredentialsException;
import com.store.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

/**
 * Manejo global de excepciones con @ControllerAdvice:
 * devuelve JSON y el código HTTP adecuado.
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Maneja credenciales inválidas lanzadas en el login,
     * devolviendo un 401 (UNAUTHORIZED) y un objeto JSON con detalles.
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentials(
            InvalidCredentialsException ex
    ) {
        Map<String, Object> responseBody = Map.of(
                "status", HttpStatus.UNAUTHORIZED.value(),
                "error", ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(responseBody);
    }

    /**
     * Lanza un HTTP 409 (Conflict) si el usuario ya existe.
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleUserExists(UserAlreadyExistsException ex) {
        Map<String, Object> body = Map.of(
                "status", HttpStatus.CONFLICT.value(),
                "error", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    /**
     * Manejo de cualquier otra RuntimeException,
     * devolviendo 500 (Internal Server Error) y un JSON con mensaje genérico.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleGenericRuntime(RuntimeException ex) {
        Map<String, Object> responseBody = Map.of(
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", "Ha ocurrido un error interno."
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseBody);
    }

}
