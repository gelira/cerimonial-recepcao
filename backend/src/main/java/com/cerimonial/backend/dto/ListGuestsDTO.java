package com.cerimonial.backend.dto;

import java.util.List;

import com.cerimonial.backend.models.Guest;

public class ListGuestsDTO {
    private List<Guest> guests;

    public ListGuestsDTO() {
    }

    public ListGuestsDTO(List<Guest> guests) {
        this.guests = guests;
    }

    public List<Guest> getGuests() {
        return guests;
    }
}
