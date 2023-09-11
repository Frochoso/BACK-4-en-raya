package com.formacion.cuatroenraya.game.application;

import com.formacion.cuatroenraya.exceptions.playerExceptions.EntityNotFoundException;
import com.formacion.cuatroenraya.game.domain.Game;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameOutputDto;
import com.formacion.cuatroenraya.game.infrastructure.repository.GameRepository;
import com.formacion.cuatroenraya.game.mapper.GameMapper;
import com.formacion.cuatroenraya.idManager.IdManager;
import com.formacion.cuatroenraya.records.application.RecordsServiceImpl;
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
    RecordsServiceImpl recordsServiceImpl;

    GameMapper gameMapper= Mappers.getMapper(GameMapper.class);

    @Override
    public Mono<GameOutputDto> createGame(Integer playerId) {

        Mono<Player> playerMono = playerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Player not found")));

        playerMono.subscribe(player -> {player.setPlayerNumber(1);
            playerRepository.save(player);
        });

        Game game = new Game();

        Integer[][] newSize = new Integer[6][7];

        for(Integer i=0;i<6;i++){
            for(Integer j=0;j<7;j++){
                newSize[i][j]=0;
            }
        }

        game.setSize(newSize);

        Mono<Game> gameMono = playerMono.flatMap( player1 -> {
            game.setPlayer1Id(player1.getId());
            game.setPlayer2Id(null);
            return gameRepository.save(game);
        });

        return gameMono
                .map(gameResult -> gameMapper.gameToGameOutputDto(gameResult));
    }

    @Override
    public Mono<Void> newMovement(IdManager idManager) {
        return gameRepository.findById(idManager.getGameId())
                .flatMap(game -> {
                    executeMove(idManager, game);
                    return gameRepository.save(game);
                })
                .then();
    }

    private void executeMove(IdManager idManager, Game game) {
        if(game.getPlayer2Id() == null) {
            throw new EntityNotFoundException("There's not a second player");
        }

        if (game.getSize()[0][idManager.getColumn()] != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Full column");
        }

        int row = -1;
        for (int i = 5; i >= 0; i--) {
            if (game.getSize()[i][idManager.getColumn()] == 0) {
                game.getSize()[i][idManager.getColumn()] = idManager.getPlayerId();
                row = i;
                break;
            }
        }

        final int finalRow = row;

        Mono<Player> playerMono = playerRepository
                .findPlayerByGameIdAndPlayerId(game.getId(), idManager.getPlayerId());

        playerMono.subscribe(player -> {
            recordsServiceImpl.createMove(game.getId(), player.getId(), finalRow, idManager.getColumn());
        });
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
    public Mono<String> checkWinner(Integer[][] size, Integer playerId) {
        for (int i = 1; i < 5; i += 1) {
            for (int j = 0; j < 6 - 3; j += 1) {
                if (size[i][j] == playerId && size[i][j + 1] == playerId
                        && size[i][j + 2] == playerId && size[i][j + 3] == playerId) {
                    return Mono.just("Player: " + playerId + " has won");
                }
            }
        }

        for (int i = 0; i < 5; i += 1) {
            for (int j = 0; j < 6 - 3; j += 1) {
                if (size[j][i] == playerId && size[j + 1][i] == playerId
                        && size[j + 2][i] == playerId && size[j + 3][i] == playerId) {
                    return Mono.just("Player: " + playerId + " has won");
                }
            }
        }

        for (int i = 0; i < 6 - 4 + 1; i += 1) {
            for (int j = 0; j < 5 - 4 + 1; j += 1) {
                if (size[j][i] == playerId && size[j + 1][i + 1] == playerId
                        && size[j + 2][i + 2] == playerId && size[j + 3][i + 3] == playerId) {
                    return Mono.just("Player: " + playerId + " has won");
                }
            }
        }

        for (int i = 6; i > 3; i -= 1) {
            for (int j = 0; j < 5 - 3; j += 1) {
                if (size[j][i - 1] == playerId && size[j + 1][i - 2] == playerId
                        && size[j + 2][i - 3] == playerId && size[j + 3][i - 4] == playerId) {
                    return Mono.just("Player: " + playerId + " has won");
                }
            }
        }

        return Mono.just("");
    }

    @Override
    public Flux<GameOutputDto> getAllGames() {
        Flux<Game> gamesFlux = gameRepository.findAll();

        return gamesFlux
                .map(gamesResult -> gameMapper.gameToGameOutputDto(gamesResult));
    }

    @Override
    public Flux<GameOutputDto> findAllOnlyOnePlayer() {
        Flux<Game> gameFlux = gameRepository.findAllOnlyOnePlayer();
        return gameFlux
                .map(gamesResult -> gameMapper.gameToGameOutputDto(gamesResult));
    }

    @Override
    public Mono<GameOutputDto> addPlayer2(Integer gameId, Integer playerId) {

        return gameRepository.findById(gameId)
                .flatMap(game -> {
                    return playerRepository.findById(playerId)
                            .flatMap(player -> {
                                game.setPlayer2Id(player.getId());
                                return gameRepository.save(game)
                                        .map(savedGame -> gameMapper.gameToGameOutputDto(savedGame));
                            });
                });
    }
}