package com.tracevia.app.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to handle validation errors.
 * This exception is thrown when input data fails validation.
 * It is annotated with {@link ResponseStatus} to return an HTTP status of 400 (BAD REQUEST).
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException{

    /**
     * Constructs a new ValidationException with the specified detail message.
     *
     * @param message The detail message.
     */
    public ValidationException(String message){
        super(message);
    }

}
