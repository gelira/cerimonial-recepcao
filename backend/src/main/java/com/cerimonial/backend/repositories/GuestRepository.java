package com.cerimonial.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;

import com.cerimonial.backend.models.Guest;

public interface GuestRepository extends SoftDeleteRepository<Guest, String> {
    @Query(value = "{ eventId: ?0, deletedAt: null }", sort = "{ name: 1 }")
    List<Guest> findByEventId(String eventId);

    @Query(value = "{ eventId: ?0, deletedAt: null }", sort = "{ name: 1 }")
    List<Guest> findByTableId(String tableId);
}
