package com.tracevia.app.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to handle resource not found errors.
 * This exception is thrown when a requested resource cannot be found.
 * It is annotated with {@link ResponseStatus} to return an HTTP status of 404 (NOT FOUND).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * Constructs a new ResourceNotFoundException with the specified detail message.
	 *
	 * @param ex The detail message.
	 */
	public ResourceNotFoundException(String ex) {
		super(ex);
	}
}