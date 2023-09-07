package com.formacion.cuatroenraya.player.application;

import com.formacion.cuatroenraya.exceptions.playerExceptions.PlayerNotFoundException;
import com.formacion.cuatroenraya.exceptions.playerExceptions.UnprocessableEntityException;
import com.formacion.cuatroenraya.player.domain.Player;
import com.formacion.cuatroenraya.player.infrastructure.controller.dto.PlayerInputDto;
import com.formacion.cuatroenraya.player.infrastructure.controller.dto.PlayerOutputDto;
import com.formacion.cuatroenraya.player.infrastructure.repository.PlayerRepository;
import com.formacion.cuatroenraya.player.mapper.PlayerMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    PlayerMapper mapper = Mappers.getMapper(PlayerMapper.class);

    public Mono<PlayerOutputDto> createPlayer(PlayerInputDto playerInputDto) throws UnprocessableEntityException {
        if (playerInputDto.getPlayerName() == null) {
            throw new UnprocessableEntityException("Name field can't be null");
        }

        Player player = mapper.playerInputDtoToPlayer(playerInputDto);

        Mono<Player> playerMono = playerRepository.save(player);

        return playerMono.map(playerResult -> mapper.playerToPlayerOutputDto(playerResult));
    }

    @Override
    public Mono<PlayerOutputDto> getPlayerById(Integer id) throws PlayerNotFoundException {

        Mono<Player> playerMono = playerRepository.findById(id);

        return playerMono
                .map(playerResult -> mapper.playerToPlayerOutputDto(playerResult))
                .switchIfEmpty(Mono.error(new PlayerNotFoundException("Player not found")));
    }

    @Override
    public Mono<PlayerOutputDto> findPlayerByName(String playerName) throws PlayerNotFoundException {

        Mono<Player> playerMono = playerRepository.findPlayerByPlayerName(playerName);

        return playerMono.map(playerResult -> mapper.playerToPlayerOutputDto(playerResult))
                .switchIfEmpty(Mono.error(new PlayerNotFoundException("Player not found")));
    }

    @Override
    public Flux<PlayerOutputDto> getAllPlayers() {

        Flux<Player> playersFlux = playerRepository.findAll();

        return playersFlux
                .map(playersResult -> mapper.playerToPlayerOutputDto(playersResult));
    }
}
