package com.fawry.task.backend.traveldestination.service;

import com.fawry.task.backend.traveldestination.exception.ResourceNotFoundException;
import com.fawry.task.backend.traveldestination.model.Destination;
import com.fawry.task.backend.traveldestination.model.User;
import com.fawry.task.backend.traveldestination.repository.DestinationRepository;
import com.fawry.task.backend.traveldestination.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final UserRepository userRepository;
    private final DestinationRepository destinationRepository;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    @Transactional
    public void addToWishlist(int destinationId){
        User user = getCurrentUser();
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found"));

        user.getWishlist().add(destination);
        userRepository.save(user);
    }
    @Transactional
    public void removeFromWishlist(int destinationId) {
        User user = getCurrentUser();
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found"));

        user.getWishlist().remove(destination);
        userRepository.save(user);
    }
    @Transactional(readOnly = true)
    public Set<Destination> getWishlist() {
        User user = getCurrentUser();
        return user.getWishlist();
    }

}
