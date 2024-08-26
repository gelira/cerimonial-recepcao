package com.cerimonial.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Document(collection = "tables")
public class Table {
    @Id
    private String id;
    @NonNull
    private String eventId;
    @NonNull
    private String identifier;
    @NonNull
    private String locationDescription;
    private int seatsTotal;
    private int seatsOccupied;
}
