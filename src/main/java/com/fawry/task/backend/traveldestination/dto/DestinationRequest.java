package com.fawry.task.backend.traveldestination.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DestinationRequest(@NotBlank(message = "Country is mandatory")
                                 String country,
                                 @NotBlank(message = "Capital is mandatory")
                                 String capital,
                                 @NotBlank(message = "Region is mandatory")
                                 String region,
                                 @NotNull(message = "Population is mandatory")
                                 @Min(value = 0, message = "Population must be non-negative")
                                 Long population,
                                 @NotBlank(message = "Currency is mandatory")
                                 String currency,
                                 @NotBlank(message = "Flag Image URL is mandatory")
                                 String flagImageUrl) {}
