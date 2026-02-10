package com.fawry.task.backend.traveldestination.dto;

public record DestinationRequest(String country,
                                 String capital,
                                 String region,
                                 Long population,
                                 String currency,
                                 String flagImageUrl) {}
