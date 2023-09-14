package com.formacion.cuatroenraya.game.infrastructure.controller;

import com.formacion.cuatroenraya.game.application.GameService;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameOutputDto;
import com.formacion.cuatroenraya.idManager.IdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/{playerId}")
    public ResponseEntity<Mono<GameOutputDto>> createGame(@PathVariable Integer playerId) {
        return new ResponseEntity<>(gameService.createGame(playerId), HttpStatus.CREATED);
    }
    @PostMapping("/movement")
    public ResponseEntity<Mono<Void>> newMovement(@RequestBody IdManager idManager) {
        return new ResponseEntity<>(gameService.newMovement(idManager), HttpStatus.CREATED);
    }

    @GetMapping("/getGame/{id}")
    public ResponseEntity<Mono<GameOutputDto>> getGameById(@PathVariable Integer id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Flux<GameOutputDto>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @PostMapping("/{gameId}/addPlayer2/{playerId}")
    public ResponseEntity<Mono<GameOutputDto>> addPlayer2(@PathVariable Integer gameId,
                                                             @PathVariable Integer playerId) {
        return new ResponseEntity<>(gameService.addPlayer2(gameId, playerId), HttpStatus.CREATED);
    }

    @GetMapping("/checkTurn/{gameId}/{playerId}")
    public ResponseEntity<Mono<Boolean>> checkPlayerTurn(@PathVariable Integer gameId, @PathVariable Integer playerId) {
        return new ResponseEntity<>(gameService.getGameById(gameId)
                .map(game -> Objects.equals(game.getTurn(), playerId)), HttpStatus.OK);
    }
}
