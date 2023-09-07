package com.formacion.cuatroenraya.moves.mapper;

import com.formacion.cuatroenraya.moves.domain.Moves;
import com.formacion.cuatroenraya.moves.infrastructure.controller.dto.MovesInputDto;
import com.formacion.cuatroenraya.moves.infrastructure.controller.dto.MovesOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MovesMapper {
    @Mapping(target="id", source="id")
    @Mapping(target="date", source="date")
    @Mapping(target="row", source="row")
    @Mapping(target="column", source="column")
    @Mapping(target="move_number", source="move_number")
    @Mapping(target="player_id", source="player_id")
    @Mapping(target="game_id", source="game_id")
    Moves movesInputDtoToMoves(MovesInputDto movesInputDto);

    @Mapping(target="id", source="id")
    @Mapping(target="date", source="date")
    @Mapping(target="row", source="row")
    @Mapping(target="column", source="column")
    @Mapping(target="move_number", source="move_number")
    @Mapping(target="player_id", source="player_id")
    @Mapping(target="game_id", source="game_id")
    MovesOutputDto movesToMovesOutputDto(Moves moves);
}