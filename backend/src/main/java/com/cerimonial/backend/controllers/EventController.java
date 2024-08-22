package com.cerimonial.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerimonial.backend.dto.ListEventsDTO;
import com.cerimonial.backend.repositories.EventRepository;

@RestController
@RequestMapping("/events")
public class EventController {
    private EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public ResponseEntity<ListEventsDTO> getAllEvents() {
        return ResponseEntity.ok(
            new ListEventsDTO(eventRepository.findAll())
        );
    }
}
