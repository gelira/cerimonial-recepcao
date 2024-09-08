package com.cerimonial.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cerimonial.backend.dto.CreateEventDTO;
import com.cerimonial.backend.models.Event;
import com.cerimonial.backend.repositories.EventRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EventService {
    private EventRepository eventRepository;

    public List<Event> listEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(CreateEventDTO createEventDTO) {
        Event event = new Event();
        event.setName(createEventDTO.getName());
        event.setDate(createEventDTO.getDate());

        return eventRepository.save(event);
    }

    public Event getEvent(String eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }
}
