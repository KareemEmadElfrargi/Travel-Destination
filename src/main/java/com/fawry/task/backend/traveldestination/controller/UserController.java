package com.fawry.task.backend.traveldestination.controller;

import com.fawry.task.backend.traveldestination.dto.ApiResponse;
import com.fawry.task.backend.traveldestination.dto.PaginatedResponse;
import com.fawry.task.backend.traveldestination.model.Destination;
import com.fawry.task.backend.traveldestination.service.DestinationService;
import com.fawry.task.backend.traveldestination.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final DestinationService destinationService;
    private final WishlistService wishlistService;

    @GetMapping("/destinations")
    public ResponseEntity<ApiResponse<PaginatedResponse<List<Destination>>>> getAllDestinations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Destination> destinations = destinationService.getAllDestinations(page,size);

        PaginatedResponse<List<Destination>> response = PaginatedResponse.<List<Destination>>builder()
                .content(destinations.getContent())
                .pageNo(destinations.getNumber())
                .pageSize(destinations.getSize())
                .totalElements(destinations.getTotalElements())
                .totalPages(destinations.getTotalPages())
                .last(destinations.isLast())
                .build();

        return ResponseEntity.ok(ApiResponse.<PaginatedResponse<List<Destination>>>builder()
                .success(true)
                .message("Destinations fetched successfully")
                .data(response)
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

    // endpoint for adding a destination to the wishlist (Want to Visit)
    @PostMapping("/wishlist/{destinationId}")
    public ResponseEntity<ApiResponse<Void>> addToWishlist(@PathVariable int destinationId) {
        wishlistService.addToWishlist(destinationId);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Added to wishlist successfully")
                .build());
    }
    @DeleteMapping("/wishlist/{destinationId}")
    public ResponseEntity<ApiResponse<Void>> removeFromWishlist(@PathVariable int destinationId) {
        wishlistService.removeFromWishlist(destinationId);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Removed from wishlist successfully")
                .build());
    }
    @GetMapping("/wishlist")
    public ResponseEntity<ApiResponse<Set<Destination>>> getWishlist() {
        Set<Destination> wishlist = wishlistService.getWishlist();
        return ResponseEntity.ok(ApiResponse.<Set<Destination>>builder()
                .success(true)
                .message("Wishlist fetched successfully")
                .data(wishlist)
                .build());
    }

}
