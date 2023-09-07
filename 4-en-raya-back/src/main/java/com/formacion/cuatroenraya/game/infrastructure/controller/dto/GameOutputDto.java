package com.formacion.cuatroenraya.game.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameOutputDto {
    private Integer id;

    private Integer player1_id;

    private Integer player2_id;

    private Integer[][] size;
}
