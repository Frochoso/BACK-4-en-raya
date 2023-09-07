package com.formacion.cuatroenraya.player.application;

import com.formacion.cuatroenraya.exceptions.playerExceptions.PlayerNotFoundException;
import com.formacion.cuatroenraya.exceptions.playerExceptions.UnprocessableEntityException;
import com.formacion.cuatroenraya.player.domain.Player;
import com.formacion.cuatroenraya.player.infrastructure.controller.dto.PlayerInputDto;
import com.formacion.cuatroenraya.player.infrastructure.controller.dto.PlayerOutputDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerService {
    Mono<PlayerOutputDto> createPlayer(PlayerInputDto playerInputDto) throws UnprocessableEntityException;

    Mono<PlayerOutputDto> getPlayerById(Integer id) throws PlayerNotFoundException;

    Mono<Player> findPlayerByName(String playerName) throws PlayerNotFoundException;

    Flux<Player> getAllPlayers();
}
