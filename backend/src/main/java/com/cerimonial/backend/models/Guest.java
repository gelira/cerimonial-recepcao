package com.cerimonial.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "guests")
public class Guest {
    @Id
    private String id;
    private String eventId;
    private String tableId;
    private String name;
    private boolean arrived;

    public Guest() {
    }

    public Guest(String eventId, String tableId, String name, boolean arrived) {
        this.eventId = eventId;
        this.tableId = tableId;
        this.name = name;
        this.arrived = arrived;
    }

    public String getEventId() {
        return eventId;
    }

    public String getTableId() {
        return tableId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isArrived() {
        return arrived;
    }    
}
