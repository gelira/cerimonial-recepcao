package com.cerimonial.backend.models;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "guests")
public class Guest {
    @Id
    private String id;
    private String eventId;
    private String tableId;
    private String name;
    private OffsetDateTime deletedAt;
}
