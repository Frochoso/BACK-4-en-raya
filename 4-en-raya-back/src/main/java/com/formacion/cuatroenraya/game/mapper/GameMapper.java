package com.formacion.cuatroenraya.game.mapper;

import com.formacion.cuatroenraya.game.domain.Game;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameInputDto;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Mono;

@Mapper
public interface GameMapper {

    @Mapping(target="player1_id",source="player1_id")
    @Mapping(target="player2_id",source="player2_id")
    @Mapping(target="size",source="size")
    Game gameInputDtoToGame(GameInputDto gameInputDto);

    GameOutputDto gameToGameOutputDto(Game game);

    Mono<GameOutputDto> monoGameToMonoGameOutputDto(Mono<Game> game);
}
