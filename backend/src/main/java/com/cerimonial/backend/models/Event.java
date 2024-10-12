package com.cerimonial.backend.models;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "events")
@NoArgsConstructor
public class Event {
    @Id
    private String id;
    private String name;
    private String date;
    private OffsetDateTime deletedAt;
}
