package com.cerimonial.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cerimonial.backend.dto.CreateEventDTO;
import com.cerimonial.backend.dto.ListEventsDTO;
import com.cerimonial.backend.models.Event;
import com.cerimonial.backend.repositories.EventRepository;
import com.cerimonial.backend.services.EventService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {
    private EventRepository eventRepository;
    private EventService eventService;

    @GetMapping
    public ListEventsDTO getAllEvents() {
        return new ListEventsDTO(eventRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@Valid @RequestBody CreateEventDTO createEventDTO) {
        return eventService.createEvent(createEventDTO);
    }
}
