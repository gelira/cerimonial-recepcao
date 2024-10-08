package com.cerimonial.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTableDTO {
    @NotBlank
    private String identifier;
    @NotNull
    private String locationDescription;
}