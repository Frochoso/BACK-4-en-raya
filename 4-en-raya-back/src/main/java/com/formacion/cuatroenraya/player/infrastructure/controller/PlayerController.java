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
    PlayerService jugadorService;

    @PostMapping()
    public ResponseEntity<Mono<PlayerOutputDto>> createPlayer(@RequestBody PlayerInputDto playerInputDto) {
        return new ResponseEntity<>(jugadorService.createPlayer(playerInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<PlayerOutputDto>> getPlayerById(@PathVariable Integer id) {
        return new ResponseEntity<>(jugadorService.getPlayerById(id), HttpStatus.OK);
    }

    @GetMapping("/{playerName}")
    public ResponseEntity<Mono<Player>> findPlayerBYName(@PathVariable String playerName) {
        return new ResponseEntity<>(jugadorService.findPlayerByName(playerName), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Flux<Player>> getAllPlayers() {
        return new ResponseEntity<>(jugadorService.getAllPlayers(), HttpStatus.OK);
    }
}
