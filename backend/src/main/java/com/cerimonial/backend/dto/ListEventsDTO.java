package com.cerimonial.backend.dto;

import java.util.List;

import com.cerimonial.backend.models.Event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListEventsDTO {
    private List<Event> events;
}
