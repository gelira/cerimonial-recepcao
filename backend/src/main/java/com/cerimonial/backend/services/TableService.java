package com.cerimonial.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cerimonial.backend.dto.CreateTableDTO;
import com.cerimonial.backend.models.Table;
import com.cerimonial.backend.repositories.TableRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TableService {
    private TableRepository tableRepository;

    public List<Table> listTables(String eventId) {
        return tableRepository.findByEventId(eventId);
    }

    public Table createTable(CreateTableDTO createTableDTO) {
        Table table = new Table();
        table.setEventId(createTableDTO.getEventId());
        table.setIdentifier(createTableDTO.getIdentifier());
        table.setLocationDescription(createTableDTO.getLocationDescription());
        table.setSeatsTotal(0);
        table.setSeatsOccupied(0);

        return tableRepository.save(table);
    }

    public Table getTable(String tableId) {
        return tableRepository.findById(tableId).orElse(null);
    }
}
