package com.cerimonial.backend.dto;

import com.cerimonial.backend.validators.StringDateFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEventDTO {
    @NotBlank
    private String name;

    @StringDateFormat
    private String date;
}
