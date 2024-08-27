package com.cerimonial.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cerimonial.backend.dto.ListGuestsDTO;
import com.cerimonial.backend.services.GuestService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/guests")
public class GuestController {
    private GuestService guestService;

    @GetMapping
    public ListGuestsDTO listTables(
        @RequestParam(value = "tableId", required = false) String tableId,
        @RequestParam(value = "eventId", required = false) String eventId
    ) {
        if (tableId == null && eventId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        ListGuestsDTO dto = new ListGuestsDTO();

        if (tableId != null) {
            dto.setGuests(guestService.listByTableId(tableId));
        } else {
            dto.setGuests(guestService.listByEventId(eventId));
        }

        return dto;
    }
}
