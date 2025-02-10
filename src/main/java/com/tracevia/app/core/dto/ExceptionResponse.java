package com.tracevia.app.core.dto;

import java.util.Date;

/**
 * Represents the response structure for an exception, containing details about the error.
 * This class is used to standardize the format of error responses sent back to the client
 * when an exception is thrown within the application.
 *
 * @param timestamp The timestamp when the exception occurred.
 * @param message A brief message describing the exception or error.
 * @param details Additional details about the exception, typically the request description or cause.
 */
public record ExceptionResponse(Date timestamp, String message, String details) {}
