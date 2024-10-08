package com.cerimonial.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cerimonial.backend.dto.CreateGuestDTO;
import com.cerimonial.backend.dto.ListGuestsDTO;
import com.cerimonial.backend.models.Guest;
import com.cerimonial.backend.models.Table;
import com.cerimonial.backend.services.GuestService;
import com.cerimonial.backend.services.TableService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/guests")
public class GuestController {
    private GuestService guestService;
    private TableService tableService;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest createGuest(@Valid @RequestBody CreateGuestDTO createGuestDTO) {
        Table table = tableService.getTable(createGuestDTO.getTableId());

        if (table == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found");
        }

        return guestService.createGuest(createGuestDTO, table.getEventId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuest(@PathVariable("id") String guestId) {
        Guest guest = guestService.getGuest(guestId);

        if (guest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        guestService.deleteGuest(guest);
    }
}
