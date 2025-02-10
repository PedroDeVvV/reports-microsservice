package com.tracevia.app.core.dto;

/**
 * Represents a request containing an access token.
 * This class is a record, which is a special kind of class in Java
 * that provides a concise way to model data as immutable objects.
 *
 * @param accessToken the access token used for authentication or API requests.
 */
public record TokenRequest(String accessToken) {}