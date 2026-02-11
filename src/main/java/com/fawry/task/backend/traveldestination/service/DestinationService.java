package com.fawry.task.backend.traveldestination.service;


import com.fawry.task.backend.traveldestination.dto.DestinationRequest;
import com.fawry.task.backend.traveldestination.model.Destination;
import com.fawry.task.backend.traveldestination.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public Destination addDestination(DestinationRequest request) {
        Destination destination = mapRequestToEntity(request);
        return destinationRepository.save(destination);
    }
    public List<Destination> addDestinations(List<DestinationRequest> requests){
        List<Destination> destinations = requests.stream()
                .map(this::mapRequestToEntity)
                .toList();
        return destinationRepository.saveAll(destinations);
    }

    public Page<Destination> getAllDestinations(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return destinationRepository.findAll(pageable);
    }

    public Destination getDestinationById(int id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
    }

    public void deleteDestination(int id) {
        if (!destinationRepository.existsById(id)) {
            throw new RuntimeException("Destination not found");
        }
        destinationRepository.deleteById(id);
    }

    public List<Destination> searchDestinations(String query) {
        return destinationRepository.findByCountryContainingIgnoreCaseOrCapitalContainingIgnoreCase(query, query);
    }

    private Destination mapRequestToEntity(DestinationRequest request) {
        return Destination.builder()
                .country(request.country())
                .capital(request.capital())
                .region(request.region())
                .population(request.population())
                .currency(request.currency())
                .flagImageUrl(request.flagImageUrl())
                .build();
    }
}

