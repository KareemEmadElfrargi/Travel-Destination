package com.fawry.task.backend.traveldestination.repository;

import com.fawry.task.backend.traveldestination.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination,Integer> {
}
