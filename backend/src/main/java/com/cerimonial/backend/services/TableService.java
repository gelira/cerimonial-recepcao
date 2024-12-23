package com.cerimonial.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cerimonial.backend.dto.CreateTableDTO;
import com.cerimonial.backend.dto.UpdateTableDTO;
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

        return tableRepository.save(table);
    }

    public Optional<Table> getTable(String tableId) {
        return tableRepository.findById(tableId);
    }

    public void deleteTable(Table table) {
        tableRepository.deleteById(table.getId());
    }

    public void updateTable(Table table, UpdateTableDTO updateTableDTO) {
        String newIdentifier = updateTableDTO.getIdentifier();
        String newLocationDescription = updateTableDTO.getLocationDescription();
        
        if (newIdentifier != null && newIdentifier != "") {
            table.setIdentifier(newIdentifier);
        }

        table.setLocationDescription(newLocationDescription);

        tableRepository.save(table);
    }
}
