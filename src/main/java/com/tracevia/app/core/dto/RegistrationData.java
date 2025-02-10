package com.tracevia.app.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrationData(@NotBlank String login, @NotBlank String password) {
}
