package com.tracevia.app.infra.exception.handler;

import com.tracevia.app.core.dto.ExceptionResponse;
import com.tracevia.app.infra.exception.AuthenticationException;
import com.tracevia.app.infra.exception.RegistrationException;
import com.tracevia.app.infra.exception.ResourceNotFoundException;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Global exception handler for the application. This class is annotated with {@link ControllerAdvice} and
 * {@link RestController} to handle exceptions globally and return standardized error responses for different types of exceptions.
 *
 * The handler includes methods to handle:
 * <ul>
 *     <li>General exceptions ({@link Exception})</li>
 *     <li>Resource not found exceptions ({@link ResourceNotFoundException})</li>
 *     <li>Authentication exceptions ({@link AuthenticationException})</li>
 * </ul>
 *
 * Each exception handler constructs an {@link ExceptionResponse} with the exception details and appropriate HTTP status codes:
 * <ul>
 *     <li>Internal server error (500) for general exceptions</li>
 *     <li>Not found (404) for resource not found exceptions</li>
 *     <li>Bad request (400) for authentication exceptions</li>
 * </ul>
 */
@ControllerAdvice
@RestController
public class ExceptionGlobalHandler {

    /**
     * Handles all general exceptions and returns a standardized error response.
     *
     * @param ex The exception that was thrown.
     * @param request The web request that triggered the exception.
     * @return A {@link ResponseEntity} containing the error details and HTTP status code 500 (INTERNAL_SERVER_ERROR).
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
            Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles resource not found exceptions and returns a standardized error response.
     *
     * @param ex The exception that was thrown.
     * @param request The web request that triggered the exception.
     * @return A {@link ResponseEntity} containing the error details and HTTP status code 404 (NOT_FOUND).
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(
            Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles authentication exceptions and returns a standardized error response.
     *
     * @param ex The exception that was thrown.
     * @param request The web request that triggered the exception.
     * @return A {@link ResponseEntity} containing the error details and HTTP status code 400 (BAD_REQUEST).
     */
    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleAuthenticationExceptions(
            Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity messagesInBody(RegistrationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
