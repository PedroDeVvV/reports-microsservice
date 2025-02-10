package com.tracevia.app.core.dto;

/**
 * A record representing the response for authenticated users.
 * <p>
 * This class encapsulates the user's unique identifier and their username,
 * typically returned as a response after successful authentication or user information retrieval.
 * </p>
 *
 * @param id       the unique identifier of the user
 * @param username the username of the user
 */
public record AuthUserResponse(Integer id, String username) {}
