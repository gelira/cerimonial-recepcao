package com.cerimonial.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Document(collection = "events")
public class Event {
    @Id
    private String id;
    @NonNull
    private String name;
    private int guestsTotal;
    private int guestsArrived;
}
