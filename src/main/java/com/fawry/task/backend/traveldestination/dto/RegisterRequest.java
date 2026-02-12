package com.fawry.task.backend.traveldestination.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank(message = "Username is mandatory")
                              @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
                              String username,
                              @NotBlank(message = "Password is mandatory")
                              @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$", message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character")
                              @Size(min = 6, message = "Password must be at least 6 characters")
                              String password
                              ) {
}
