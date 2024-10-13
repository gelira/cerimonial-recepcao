package com.cerimonial.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cerimonial.backend.dto.CreateEventDTO;
import com.cerimonial.backend.dto.UpdateEventDTO;
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

    public Optional<Event> getEvent(String eventId) {
        return eventRepository.findById(eventId);
    }

    public void updateEvent(Event event, UpdateEventDTO updateEventDTO) {
        String newName = updateEventDTO.getName();
        String newDate = updateEventDTO.getDate();
        
        boolean toUpdate = false;
        
        if (newName != null && newName != "") {
            toUpdate = true;

            event.setName(newName);
        }

        if (newDate != null && newDate != "") {
            toUpdate = true;

            event.setDate(newDate);
        }

        if (toUpdate) {
            eventRepository.save(event);
        }
    }

    public void deleteEvent(Event event) {
        eventRepository.deleteById(event.getId());
    }
}
