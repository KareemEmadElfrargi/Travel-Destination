package com.fawry.task.backend.traveldestination.controller;

import com.fawry.task.backend.traveldestination.dto.ApiResponse;
import com.fawry.task.backend.traveldestination.model.Destination;
import com.fawry.task.backend.traveldestination.repository.DestinationRepository;
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

    @GetMapping("/suggestions")
    public ResponseEntity<ApiResponse<List<Destination>>> getSuggestions() {
        List<Destination> destinations = externalApiService.fetchDestinations();
        return ResponseEntity.ok(
                ApiResponse.<List<Destination>>builder()
                        .success(true)
                        .message("Suggestions fetched successfully")
                        .data(destinations).build());
    }

    @PostMapping("/destinations")
    public ResponseEntity<ApiResponse<Destination>> addDestination(@RequestBody Destination destination) {
        Destination savedDestination = destinationRepository.save(destination);
        return ResponseEntity.ok(
                ApiResponse.<Destination>builder()
                        .success(true)
                        .message("Destination added successfully")
                        .data(savedDestination).build());
    }

    @DeleteMapping("/destinations/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDestination(@PathVariable int id) {
        destinationRepository.deleteById(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Destination deleted successfully")
                .build());
    }

}
