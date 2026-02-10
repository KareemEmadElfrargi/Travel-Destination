package com.fawry.task.backend.traveldestination.controller;

import com.fawry.task.backend.traveldestination.dto.ApiResponse;
import com.fawry.task.backend.traveldestination.model.Destination;
import com.fawry.task.backend.traveldestination.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final DestinationService destinationService;

    @GetMapping("/destinations")
    public ResponseEntity<ApiResponse<List<Destination>>> getAllDestinations() {
        List<Destination> destinations = destinationService.getAllDestinations();
        return ResponseEntity.ok(ApiResponse.<List<Destination>>builder()
                .success(true)
                .message("Destinations fetched successfully")
                .data(destinations)
                .build());
    }
    @GetMapping("/destinations/{id}")
    public ResponseEntity<ApiResponse<Destination>> getDestinationById(@PathVariable int id) {
        Destination destination = destinationService.getDestinationById(id);
        return ResponseEntity.ok(ApiResponse.<Destination>builder()
                .success(true)
                .message("Destination fetched successfully")
                .data(destination)
                .build());
    }
    @GetMapping("/destinations/search")
    public ResponseEntity<ApiResponse<List<Destination>>> searchDestinations(@RequestParam String query) {
        List<Destination> destinations = destinationService.searchDestinations(query);
        return ResponseEntity.ok(ApiResponse.<List<Destination>>builder()
                .success(true)
                .message("Destinations searched successfully")
                .data(destinations)
                .build());
    }

}
