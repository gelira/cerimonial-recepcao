package com.cerimonial.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cerimonial.backend.models.Guest;

public interface GuestRepository extends MongoRepository<Guest, String> {
    @Query(sort = "{name: 1}")
    List<Guest> findByEventId(String eventId);

    @Query(sort = "{name: 1}")
    List<Guest> findByTableId(String tableId);
}
