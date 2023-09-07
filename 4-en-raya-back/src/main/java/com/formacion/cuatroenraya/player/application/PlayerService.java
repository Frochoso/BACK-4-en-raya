package com.formacion.cuatroenraya.player.application;

import com.formacion.cuatroenraya.exceptions.playerExceptions.EntityNotFoundException;
import com.formacion.cuatroenraya.exceptions.playerExceptions.UnprocessableEntityException;
import com.formacion.cuatroenraya.player.infrastructure.controller.dto.PlayerInputDto;
import com.formacion.cuatroenraya.player.infrastructure.controller.dto.PlayerOutputDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerService {
    Mono<PlayerOutputDto> createPlayer(PlayerInputDto playerInputDto) throws UnprocessableEntityException;

    Mono<PlayerOutputDto> getPlayerById(Integer id) throws EntityNotFoundException;

    Mono<PlayerOutputDto> findPlayerByName(String playerName) throws EntityNotFoundException;

    Flux<PlayerOutputDto> getAllPlayers();
}
