package com.cerimonial.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String name;
    private int guestsTotal;
    private int guestsArrived;

    public Event() {
    }

    public Event(String name, int guestsTotal, int guestsArrived) {
        this.name = name;
        this.guestsTotal = guestsTotal;
        this.guestsArrived = guestsArrived;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGuestsTotal() {
        return guestsTotal;
    }
    
    public int getGuestsArrived() {
        return guestsArrived;
    }
}
