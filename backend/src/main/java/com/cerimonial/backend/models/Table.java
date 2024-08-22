package com.cerimonial.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tables")
public class Table {
    @Id
    private String id;
    private String eventId;
    private String identifier;
    private String locationDescription;
    private int seatsTotal;
    private int seatsOccupied;

    public Table() {
    }

    public Table(String eventId, String identifier, String locationDescription, int seatsTotal, int seatsOccupied) {
        this.eventId = eventId;
        this.identifier = identifier;
        this.locationDescription = locationDescription;
        this.seatsTotal = seatsTotal;
        this.seatsOccupied = seatsOccupied;
    }

    public String getEventId() {
        return eventId;
    }

    public String getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public int getSeatsTotal() {
        return seatsTotal;
    }

    public int getSeatsOccupied() {
        return seatsOccupied;
    }
}
