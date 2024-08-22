package com.cerimonial.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cerimonial.backend.models.Guest;

public interface GuestRepository extends MongoRepository<Guest, String> {
    List<Guest> findByEventId(String eventId);
    List<Guest> findByTableId(String tableId);
}
