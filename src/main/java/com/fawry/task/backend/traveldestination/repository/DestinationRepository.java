package com.fawry.task.backend.traveldestination.repository;

import com.fawry.task.backend.traveldestination.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,Integer> {
}
