package com.formacion.cuatroenraya.player.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerOutputDto {

    private Integer id;

    private Integer playerNumber;

    private String playerName;
}
