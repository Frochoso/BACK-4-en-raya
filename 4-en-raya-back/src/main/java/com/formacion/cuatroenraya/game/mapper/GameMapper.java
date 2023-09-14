package com.formacion.cuatroenraya.game.mapper;

import com.formacion.cuatroenraya.game.domain.Game;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameInputDto;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GameMapper {

    @Mapping(target="player1Id",source="player1Id")
    @Mapping(target="player2Id",source="player2Id")
    @Mapping(target="size",source="size")
    @Mapping(target = "turn", source = "turn")
    Game gameInputDtoToGame(GameInputDto gameInputDto);

    GameOutputDto gameToGameOutputDto(Game game);
}
