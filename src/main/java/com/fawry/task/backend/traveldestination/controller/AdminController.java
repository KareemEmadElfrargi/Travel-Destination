package com.fawry.task.backend.traveldestination.controller;

import com.fawry.task.backend.traveldestination.dto.ApiResponse;
import com.fawry.task.backend.traveldestination.dto.DestinationRequest;
import com.fawry.task.backend.traveldestination.model.Destination;
import com.fawry.task.backend.traveldestination.repository.DestinationRepository;
import com.fawry.task.backend.traveldestination.service.DestinationService;
import com.fawry.task.backend.traveldestination.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ExternalApiService externalApiService;
    private final DestinationRepository destinationRepository;
    private final DestinationService destinationService;


    @GetMapping("/suggestions")
    public ResponseEntity<ApiResponse<List<Destination>>> getSuggestions() {
        List<Destination> destinations = externalApiService.fetchDestinations();

        return ResponseEntity.ok(ApiResponse.success(destinations, "Suggestions fetched successfully"));
    }

    @PostMapping("/destinations")
    public ResponseEntity<ApiResponse<Destination>> addDestination(@RequestBody DestinationRequest request) {
        Destination savedDestination = destinationService.addDestination(request);

        return ResponseEntity.ok(ApiResponse.success(savedDestination, "Destination added successfully"));

    }
    @PostMapping("/destinations/bulk")
    public ResponseEntity<ApiResponse<List<Destination>>> addDestinationsBulk(@RequestBody List<DestinationRequest> requests) {
        List<Destination> savedDestinations = destinationService.addDestinations(requests);
        return ResponseEntity.ok(ApiResponse.success(savedDestinations, "Destinations bulk added successfully"));
    }

    @DeleteMapping("/destinations/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDestination(@PathVariable int id) {
        destinationRepository.deleteById(id);

        return ResponseEntity.ok(ApiResponse.success(null, "Destination deleted successfully"));

    }



}
