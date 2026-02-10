package com.fawry.task.backend.traveldestination.service;

import com.fawry.task.backend.traveldestination.dto.CountryDto;
import com.fawry.task.backend.traveldestination.model.Destination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExternalApiService {
    private final RestTemplate restTemplate;
    @Value("${restcountries.api.url}")
    private String API_URL;

    public List<Destination> fetchDestinations() {
        CountryDto[] countries = restTemplate.getForObject(API_URL, CountryDto[].class);

        if (countries == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(countries)
                .map(this::mapCountryToDestination)
                .collect(Collectors.toList());
    }

    private Destination mapCountryToDestination(CountryDto countryDto) {

        String currencyCode = "N/A";
        if (countryDto.getCurrencies() != null && !countryDto.getCurrencies().isEmpty()) {
            Map.Entry<String, CountryDto.Currency> entry = countryDto.getCurrencies().entrySet().iterator().next();
            currencyCode = entry.getValue().getName() + " (" + entry.getValue().getSymbol() + ")";
        }

        return Destination.builder()
                .country(countryDto.getName().getCommon())
                .capital(countryDto.getCapital() != null && !countryDto.getCapital().isEmpty() ? countryDto.getCapital().get(0) : "N/A")
                .region(countryDto.getRegion())
                .population(countryDto.getPopulation())
                .currency(currencyCode)
                .flagImageUrl(countryDto.getFlags().getPng())
                .build();

    }


}
