package com.cerimonial.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Document(collection = "guests")
public class Guest {
    @Id
    private String id;
    @NonNull
    private String eventId;
    @NonNull
    private String tableId;
    @NonNull
    private String name;
    private boolean arrived;
}
