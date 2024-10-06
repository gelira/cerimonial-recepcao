package com.cerimonial.backend.dto;

import com.cerimonial.backend.validators.StringDateFormat;

import lombok.Data;

@Data
public class UpdateEventDTO {
    private String name;

    @StringDateFormat
    private String date;
}
