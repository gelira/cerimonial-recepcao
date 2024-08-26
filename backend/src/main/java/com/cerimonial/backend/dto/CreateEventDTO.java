package com.cerimonial.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEventDTO {
    @NotBlank
    private String name;
}
