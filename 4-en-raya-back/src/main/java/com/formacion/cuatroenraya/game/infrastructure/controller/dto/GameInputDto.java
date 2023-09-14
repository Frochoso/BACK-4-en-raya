package com.formacion.cuatroenraya.game.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameInputDto {

    private Integer player1Id;

    private Integer player2Id;

    private Integer[][] size;

    private Integer turn;

}
