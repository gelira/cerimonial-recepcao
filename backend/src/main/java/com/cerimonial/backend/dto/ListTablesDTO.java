package com.cerimonial.backend.dto;

import java.util.List;

import com.cerimonial.backend.models.Table;

public class ListTablesDTO {
    private List<Table> tables;

    public ListTablesDTO() {
    }

    public ListTablesDTO(List<Table> tables) {
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }
}
