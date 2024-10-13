package com.cerimonial.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cerimonial.backend.dto.CreateEventDTO;
import com.cerimonial.backend.dto.ListEventsDTO;
import com.cerimonial.backend.dto.UpdateEventDTO;
import com.cerimonial.backend.models.Event;
import com.cerimonial.backend.services.EventService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {
    private EventService eventService;

    @GetMapping
    public ListEventsDTO getAllEvents() {
        return new ListEventsDTO(eventService.listEvents());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@Valid @RequestBody CreateEventDTO createEventDTO) {
        return eventService.createEvent(createEventDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEvent(
        @PathVariable("id") String eventId,
        @Valid @RequestBody UpdateEventDTO updateEventDTO
    ) {
        Event event = eventService.getEvent(eventId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        eventService.updateEvent(event, updateEventDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable("id") String eventId) {
        Event event = eventService.getEvent(eventId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        eventService.deleteEvent(event);
    }
}
