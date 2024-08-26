package com.cerimonial.backend.dto;

import java.util.List;

import com.cerimonial.backend.models.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListTablesDTO {
    private List<Table> tables;
}
