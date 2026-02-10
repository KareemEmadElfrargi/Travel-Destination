package com.fawry.task.backend.traveldestination.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CountryDto {
    @JsonProperty("name")
    private Name name;

    @JsonProperty("capital")
    private List<String> capital;

    @JsonProperty("region")
    private String region;

    @JsonProperty("population")
    private Long population;

    @JsonProperty("currencies")
    private Map<String, Currency> currencies;

    @JsonProperty("flags")
    private Flags flags;
    @Data
    public static class Name {
        private String common;
    }

    @Data
    public static class Currency {
        private String name;
        private String symbol;
    }

    @Data
    public static class Flags {
        private String png;
    }
}
