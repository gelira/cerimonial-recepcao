package com.cerimonial.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTableDTO {
    @NotBlank
    private String eventId;
    @NotBlank
    private String identifier;
    @NotNull
    private String locationDescription;
}
