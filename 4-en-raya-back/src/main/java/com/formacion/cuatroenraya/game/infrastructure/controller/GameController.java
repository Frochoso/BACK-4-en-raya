package com.formacion.cuatroenraya.game.infrastructure.controller;

import com.formacion.cuatroenraya.game.application.GameService;
import com.formacion.cuatroenraya.game.domain.Game;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameInputDto;
import com.formacion.cuatroenraya.game.infrastructure.controller.dto.GameOutputDto;
import com.formacion.cuatroenraya.idManager.IdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/movement")
    public ResponseEntity<Mono<Void>> newMovement(@RequestBody GameInputDto gameInputDto,
                                               @RequestBody IdManager idManager) {
        return new ResponseEntity<>(gameService.newMovement(gameInputDto, idManager), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<GameOutputDto>> getGameById(@PathVariable Integer id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }

    @PostMapping("/winner")
    public ResponseEntity<Mono<Boolean>> isWinner(@RequestBody Game game) {
        return new ResponseEntity<>(gameService.getLastMove(game.getId()).defaultIfEmpty(1)
                .flatMap(playerId ->
                        gameService.checkWinner(game.getSize(), playerId)), HttpStatus.OK);
    }

    @GetMapping("/tablero/{id}")
    public ResponseEntity<Mono<GameOutputDto>> partida(@PathVariable int id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }


    @GetMapping("/partidas")
    public ResponseEntity<Flux<GameOutputDto>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

}
