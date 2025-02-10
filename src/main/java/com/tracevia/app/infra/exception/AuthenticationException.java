package com.tracevia.app.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to handle authentication errors.
 * This exception is thrown when an authentication failure occurs.
 * It is annotated with {@link ResponseStatus} to return an HTTP status of 401 (UNAUTHORIZED).
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException{

    /**
     * Constructs a new AuthenticationException with the specified detail message.
     *
     * @param message The detail message.
     */
    public AuthenticationException(String message){
        super(message);
    }

}
