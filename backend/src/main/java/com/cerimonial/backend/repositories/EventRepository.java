package com.cerimonial.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cerimonial.backend.models.Event;

public interface EventRepository extends MongoRepository<Event, String> {
}
