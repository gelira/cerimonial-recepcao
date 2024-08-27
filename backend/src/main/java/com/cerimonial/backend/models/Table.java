package com.cerimonial.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "tables")
@NoArgsConstructor
public class Table {
    @Id
    private String id;
    private String eventId;
    private String identifier;
    private String locationDescription;
}
