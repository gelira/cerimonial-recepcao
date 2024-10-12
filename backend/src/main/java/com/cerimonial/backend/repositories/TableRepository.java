package com.cerimonial.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;

import com.cerimonial.backend.models.Table;

public interface TableRepository extends SoftDeleteRepository<Table, String> {
    @Query(value = "{ eventId: ?0, deletedAt: null }", sort = "{ identifier: 1 }")
    List<Table> findByEventId(String eventId);
}
