package com.formacion.cuatroenraya.player.infrastructure.controller;

import com.formacion.cuatroenraya.player.application.PlayerService;
import com.formacion.cuatroenraya.player.domain.Player;
import com.formacion.cuatroenraya.player.infrastructure.controller.dto.PlayerInputDto;
import com.formacion.cuatroenraya.player.infrastructure.controller.dto.PlayerOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/player")
@Slf4j
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @PostMapping()
    public ResponseEntity<Mono<PlayerOutputDto>> createPlayer(@RequestBody PlayerInputDto playerInputDto) {
        return new ResponseEntity<>(playerService.createPlayer(playerInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Mono<PlayerOutputDto>> getPlayerById(@PathVariable Integer id) {
        return new ResponseEntity<>(playerService.getPlayerById(id), HttpStatus.OK);
    }

    @GetMapping("/findByName/{playerName}")
    public ResponseEntity<Mono<PlayerOutputDto>> findPlayerByName(@PathVariable String playerName) {
        return new ResponseEntity<>(playerService.findPlayerByName(playerName), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Flux<PlayerOutputDto>> getAllPlayers() {
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }

    @GetMapping("/findPlayer/{playerId}/findByGame/{gameId}")
    public ResponseEntity<Mono<PlayerOutputDto>> findPlayerByGameIdAndPlayerId(@PathVariable Integer gameId,
                                                                               @PathVariable Integer playerId) {
        return new ResponseEntity<>(playerService.findPlayerByGameIdAndPlayerId(gameId, playerId), HttpStatus.OK);
    }
}
