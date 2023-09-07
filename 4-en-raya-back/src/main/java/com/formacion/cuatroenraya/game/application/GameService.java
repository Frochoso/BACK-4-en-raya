package com.formacion.cuatroenraya.game.application;

import com.formacion.cuatroenraya.exceptions.playerExceptions.EntityNotFoundException;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameInputDto;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameOutputDto;
import com.formacion.cuatroenraya.idManager.IdManager;
import com.formacion.cuatroenraya.player.domain.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameService {

    Mono<GameOutputDto> createGame(Player player);
    Mono<Void> newMovement(GameInputDto gameInputDto, IdManager idManager);
    Mono<GameOutputDto> getGameById(Integer id) throws EntityNotFoundException;
    Mono<Integer> getLastMove(Integer gameId);
    Mono<Boolean> checkWinner(Integer[][] size, Integer playerId);
    Flux<GameOutputDto> getAllGames();

}
