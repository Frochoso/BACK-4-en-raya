package com.formacion.cuatroenraya.moves.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MovesInputDto {
    Integer id;
    LocalDateTime date;
    Integer row;
    Integer column;
    Integer playerId;
    Integer gameId;
}
