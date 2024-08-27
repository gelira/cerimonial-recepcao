package com.cerimonial.backend.dto;

import java.util.List;

import com.cerimonial.backend.models.Guest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListGuestsDTO {
    private List<Guest> guests;
}
