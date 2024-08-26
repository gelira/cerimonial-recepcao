package com.cerimonial.backend.services;

import org.springframework.stereotype.Service;

import com.cerimonial.backend.dto.CreateEventDTO;
import com.cerimonial.backend.models.Event;
import com.cerimonial.backend.repositories.EventRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EventService {
    private EventRepository eventRepository;

    public Event createEvent(CreateEventDTO createEventDTO) {
        Event event = new Event();
        event.setName(createEventDTO.getName());
        event.setGuestsArrived(0);
        event.setGuestsTotal(0);

        return eventRepository.save(event);
    }
}
