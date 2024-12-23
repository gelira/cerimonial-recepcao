package com.cerimonial.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cerimonial.backend.dto.CreateTableDTO;
import com.cerimonial.backend.dto.ListTablesDTO;
import com.cerimonial.backend.dto.UpdateTableDTO;
import com.cerimonial.backend.models.Table;
import com.cerimonial.backend.services.EventService;
import com.cerimonial.backend.services.TableService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/tables")
public class TableController {
    private TableService tableService;
    private EventService eventService;

    @GetMapping
    public ListTablesDTO listTables(@RequestParam(value = "eventId") String eventId) {
        return new ListTablesDTO(tableService.listTables(eventId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Table createTable(@Valid @RequestBody CreateTableDTO createTableDTO) {
        eventService.getEvent(createTableDTO.getEventId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        return tableService.createTable(createTableDTO);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTable(@PathVariable("id") String tableId) {
        Table table = tableService.getTable(tableId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        tableService.deleteTable(table);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTable(
        @PathVariable("id") String tableId,
        @Valid @RequestBody UpdateTableDTO updateTableDTO
    ) {
        Table table = tableService.getTable(tableId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        tableService.updateTable(table, updateTableDTO);
    }
}
