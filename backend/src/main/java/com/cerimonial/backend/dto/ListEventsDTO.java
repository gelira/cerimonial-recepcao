package com.cerimonial.backend.dto;

import java.util.List;

import com.cerimonial.backend.models.Event;

public class ListEventsDTO {
    private List<Event> events;

    public ListEventsDTO() {
    }

    public ListEventsDTO(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }
}
