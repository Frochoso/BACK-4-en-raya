package com.formacion.cuatroenraya.game.application;

import com.formacion.cuatroenraya.exceptions.playerExceptions.NoContentException;
import com.formacion.cuatroenraya.exceptions.playerExceptions.EntityNotFoundException;
import com.formacion.cuatroenraya.game.domain.Game;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameOutputDto;
import com.formacion.cuatroenraya.game.infrastructure.repository.GameRepository;
import com.formacion.cuatroenraya.game.mapper.GameMapper;
import com.formacion.cuatroenraya.idManager.IdManager;
import com.formacion.cuatroenraya.player.infrastructure.repository.PlayerRepository;
import com.formacion.cuatroenraya.records.application.RecordsServiceImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    RecordsServiceImpl recordsServiceImpl;

    GameMapper gameMapper = Mappers.getMapper(GameMapper.class);

    @Override
    public Mono<GameOutputDto> createGame(Integer playerId) {
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Player not found")))
                .flatMap(player -> {
                    player.setPlayerNumber(1);
                    return playerRepository.save(player);
                })
                .then(Mono.defer(() -> {
                    Game game = new Game();

                    Integer[][] newSize = {
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0}
                    };

                    game.setSize(newSize);
                    game.setPlayer1Id(playerId);
                    game.setPlayer2Id(null);

                    return gameRepository.save(game);
                }))
                .map(gameResult -> gameMapper.gameToGameOutputDto(gameResult));

    }

    @Override
    public Mono<Void> newMovement(IdManager idManager) {
        return gameRepository.findById(idManager.getGameId())
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Game not found")))
                .flatMap(game -> {
                    return executeMove(idManager, game)
                            .then(finishMove(game, idManager.getPlayerId()));
                })
                .then();
    }

    public Mono<Void> finishMove(Game game, Integer playerId) {

        game.setWinner(checkWinner(game.getSize(), playerId));
        return gameRepository.save(game).then();
    }

    private Mono<Void> executeMove(IdManager idManager, Game game) {
        if (game.getPlayer2Id() == null) {
            throw new EntityNotFoundException("There's not a second player");
        }

        if (game.getSize()[0][idManager.getColumn()] != 0) {
            throw new NoContentException("Full column");
        }

        int row = -1;
        for (int i = 5; i >= 0; i--) {
            if (game.getSize()[i][idManager.getColumn()] == 0) {
                game.getSize()[i][idManager.getColumn()] = idManager.getPlayerId();
                row = i;
                break;
            }
        }

        idManager.setRow(row);
        return playerRepository
                .findPlayerByGameIdAndPlayerId(game.getId(), idManager.getPlayerId())
                .flatMap(player -> recordsServiceImpl.createMove(game.getId(), player.getId(), idManager.getRow(),
                        idManager.getColumn()))
                .then();
    }

    @Override
    public int checkWinner(Integer[][] size, Integer playerId) {

        Integer counter = 0;

        // Comprueba el empate - Devuelve 0 si hay empate
        for (int i = 0; i < 6; i++) {
            if (size[5][i] != 0) {
                counter++;
            }

            if (counter >= 7) {
                return 0;
            }
        }

        // Casos de victoria - Devuelve el Id del ganador
        for (int i = 1; i < 5; i += 1) {
            for (int j = 0; j < 6 - 3; j += 1) {
                if (size[i][j] == playerId && size[i][j + 1] == playerId
                        && size[i][j + 2] == playerId && size[i][j + 3] == playerId) {
                    return playerId;
                }
            }
        }

        for (int i = 0; i < 5; i += 1) {
            for (int j = 0; j < 6 - 3; j += 1) {
                if (size[j][i] == playerId && size[j + 1][i] == playerId
                        && size[j + 2][i] == playerId && size[j + 3][i] == playerId) {
                    return playerId;

                }
            }
        }

        for (int i = 0; i < 6 - 4 + 1; i += 1) {
            for (int j = 0; j < 5 - 4 + 1; j += 1) {
                if (size[j][i] == playerId && size[j + 1][i + 1] == playerId
                        && size[j + 2][i + 2] == playerId && size[j + 3][i + 3] == playerId) {
                    return playerId;

                }
            }
        }

        for (int i = 6; i > 3; i -= 1) {
            for (int j = 0; j < 5 - 3; j += 1) {
                if (size[j][i - 1] == playerId && size[j + 1][i - 2] == playerId
                        && size[j + 2][i - 3] == playerId && size[j + 3][i - 4] == playerId) {
                    return playerId;

                }
            }
        }

        return -1; // La partida continua, no ha terminado
    }

    @Override
    public Mono<GameOutputDto> getGameById(Integer id) throws EntityNotFoundException {

        Mono<Game> gameMono = gameRepository.findById(id);

        return gameMono
                .map(gameResult -> gameMapper.gameToGameOutputDto(gameResult))
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Game not found")));
    }

    @Override
    public Flux<GameOutputDto> getAllGames() {
        Flux<Game> gamesFlux = gameRepository.findAll();

        return gamesFlux
                .map(gamesResult -> gameMapper.gameToGameOutputDto(gamesResult));
    }

    @Override
    public Mono<GameOutputDto> addPlayer2(Integer gameId, Integer playerId) {

        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Game not found")))
                .flatMap(game -> playerRepository.findById(playerId)
                        .switchIfEmpty(Mono.error(new EntityNotFoundException("Player not found")))
                        .flatMap(player -> {
                            player.setPlayerNumber(2);
                            game.setPlayer2Id(player.getId());
                            return playerRepository.save(player)
                                    .then(gameRepository.save(game))
                                    .map(savedGame -> gameMapper.gameToGameOutputDto(savedGame));
                        }));

    }
}