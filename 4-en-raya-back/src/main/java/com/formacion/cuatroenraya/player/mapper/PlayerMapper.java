package com.formacion.cuatroenraya.player.mapper;

import com.formacion.cuatroenraya.player.domain.Player;
import com.formacion.cuatroenraya.player.infrastructure.controller.dto.PlayerInputDto;
import com.formacion.cuatroenraya.player.infrastructure.controller.dto.PlayerOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PlayerMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "playerName", source = "playerName")
    Player jugadorInputToJugador(PlayerInputDto playerInputDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "playerName", source = "playerName")
    PlayerOutputDto jugadorToJugadorOutputDto(Player player);
}
