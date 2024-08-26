package com.cerimonial.backend.dto;

import java.util.List;

import com.cerimonial.backend.models.Guest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListGuestsDTO {
    private List<Guest> guests;
}
