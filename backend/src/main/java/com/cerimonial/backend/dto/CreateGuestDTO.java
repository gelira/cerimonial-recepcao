package com.cerimonial.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateGuestDTO {
    @NotBlank
    private String tableId;
    @NotBlank
    private String name;
}
