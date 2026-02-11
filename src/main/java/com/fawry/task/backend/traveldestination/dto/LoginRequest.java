package com.fawry.task.backend.traveldestination.dto;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(@NotNull(message = "Username is mandatory")
                           String username,
                           @NotNull(message = "Password is mandatory")
                           String password) {
}
