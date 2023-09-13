package com.formacion.cuatroenraya.records.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RecordsInputDto {
    Integer id;
    LocalDate recordDate;
    Integer recordRow;
    Integer recordColumn;
    Integer playerId;
    Integer gameId;
}
