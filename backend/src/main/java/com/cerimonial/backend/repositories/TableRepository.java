package com.cerimonial.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cerimonial.backend.models.Table;

public interface TableRepository extends MongoRepository<Table, String> {
    List<Table> findByEventId(String eventId);
}
