package com.formacion.cuatroenraya.game.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameOutputDto {
    private Integer id;

    private Integer player1Id;

    private Integer player2Id;

    private Integer[][] size;

    private Integer winner;

    private Integer turn;
}
