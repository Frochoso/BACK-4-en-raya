package com.formacion.cuatroenraya.game.application;

import com.formacion.cuatroenraya.exceptions.playerExceptions.EntityNotFoundException;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameOutputDto;
import com.formacion.cuatroenraya.idManager.IdManager;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameService {

    Mono<GameOutputDto> createGame(Integer playerId);
    Mono<Void> newMovement(IdManager idManager);
    Mono<GameOutputDto> getGameById(Integer id) throws EntityNotFoundException;
    int checkWinner(Integer[][] size, Integer playerId);
    Flux<GameOutputDto> getAllGames();
    Mono<GameOutputDto> addPlayer2(Integer gameId, Integer playerId);

}
