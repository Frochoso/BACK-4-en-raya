package com.formacion.cuatroenraya.game.application;

import com.formacion.cuatroenraya.exceptions.playerExceptions.EntityNotFoundException;
import com.formacion.cuatroenraya.game.domain.Game;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameInputDto;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameOutputDto;
import com.formacion.cuatroenraya.game.infrastructure.repository.GameRepository;
import com.formacion.cuatroenraya.game.mapper.GameMapper;
import com.formacion.cuatroenraya.idManager.IdManager;
import com.formacion.cuatroenraya.moves.application.MovesServiceImpl;
import com.formacion.cuatroenraya.player.domain.Player;
import com.formacion.cuatroenraya.player.infrastructure.repository.PlayerRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    MovesServiceImpl movesService;

    GameMapper gameMapper= Mappers.getMapper(GameMapper.class);

    @Override
    public Mono<GameOutputDto> createGame(Player player) {

        player.setPlayerNumber(1);
        Mono<Player> playerMono = playerRepository.save(player);

        Game game = new Game();
        game.setSize(new Integer[6][7]);

        return playerMono.flatMap( player1 -> {
            game.setPlayer1_id(player1.getId());
            game.setPlayer2_id(null);
            return gameMapper.monoGameToMonoGameOutputDto(gameRepository.save(game));
        });
    }

    @Override
    public Mono<Void> newMovement(GameInputDto gameInputDto, IdManager idManager){
        Mono<Game> gameMono = gameRepository.findById(idManager.getGameId());
        return Mono.just(gameMono.subscribe(gameMono1 -> executeMove(idManager, gameMono1)))
                .then();
    }

    private void executeMove(IdManager idManager, Game game) {
        if (game.getSize()[0][idManager.getColumn()] != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Full column");
        }

        int row = -1;
        for (int i = 5; i >= 0; i--) {
            if (game.getSize()[i][idManager.getRow()] == 0) {
                game.getSize()[i][idManager.getColumn()] = idManager.getPlayerId();
                row = i;
                break;
            }
        }

        gameRepository.save(game).subscribe();

        Mono<Player> playerMono = playerRepository
                .findPlayerByGameIdAndPlayerId(game.getId(),idManager.getPlayerId());
        int finalFila = row;
        playerMono.subscribe(player ->
                movesService.createMove(game.getId(), player.getId(), finalFila, idManager.getColumn()));
    }

    @Override
    public Mono<GameOutputDto> getGameById(Integer id) throws EntityNotFoundException {

        Mono<Game> gameMono = gameRepository.findById(id);

        return gameMono
                .map(gameResult -> gameMapper.gameToGameOutputDto(gameResult))
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Game not found")));
    }

    @Override
    public Mono<Integer> getLastMove(Integer gameId) {
        return gameRepository.findLastMove(gameId);
    }

    @Override
    public Mono<Boolean> checkWinner(Integer[][] size, Integer playerId) {
        for (int i = 1; i < 5; i += 1) {
            for (int j = 0; j < 6 - 3; j += 1) {
                if (size[i][j] == playerId && size[i][j + 1] == playerId
                        && size[i][j + 2] == playerId && size[i][j + 3] == playerId) {
                    return Mono.just(true);
                }
            }
        }

        for (int i = 0; i < 5; i += 1) {
            for (int j = 0; j < 6 - 3; j += 1) {
                if (size[j][i] == playerId && size[j + 1][i] == playerId
                        && size[j + 2][i] == playerId && size[j + 3][i] == playerId) {
                    return Mono.just(true);
                }
            }
        }

        for (int i = 0; i < 6 - 4 + 1; i += 1) {
            for (int j = 0; j < 5 - 4 + 1; j += 1) {
                if (size[j][i] == playerId && size[j + 1][i + 1] == playerId
                        && size[j + 2][i + 2] == playerId && size[j + 3][i + 3] == playerId) {
                    return Mono.just(true);
                }
            }
        }

        for (int i = 6; i > 3; i -= 1) {
            for (int j = 0; j < 5 - 3; j += 1) {
                if (size[j][i - 1] == playerId && size[j + 1][i - 2] == playerId
                        && size[j + 2][i - 3] == playerId && size[j + 3][i - 4] == playerId) {
                    return Mono.just(true);
                }
            }
        }

        return Mono.just(false);
    }

    @Override
    public Flux<GameOutputDto> getAllGames() {
        Flux<Game> gamesFlux = gameRepository.findAll();

        return gamesFlux
                .map(gamesResult -> gameMapper.gameToGameOutputDto(gamesResult));
    }
}