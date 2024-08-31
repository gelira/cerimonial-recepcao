package com.cerimonial.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cerimonial.backend.models.Table;

public interface TableRepository extends MongoRepository<Table, String> {
    @Query(sort = "{identifier: 1}")
    List<Table> findByEventId(String eventId);
}
