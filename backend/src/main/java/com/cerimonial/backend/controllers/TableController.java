package com.cerimonial.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cerimonial.backend.dto.ListTablesDTO;
import com.cerimonial.backend.repositories.TableRepository;

@RestController
@RequestMapping("/tables")
public class TableController {
    private TableRepository tableRepository;

    public TableController(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @GetMapping
    public ListTablesDTO listTables(@RequestParam(value = "eventId") String eventId) {
        return new ListTablesDTO(tableRepository.findByEventId(eventId));
    }
}
