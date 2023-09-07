package com.formacion.cuatroenraya.moves.infrastructure.controller;

import com.formacion.cuatroenraya.moves.application.MovesService;
import com.formacion.cuatroenraya.moves.infrastructure.controller.dto.MovesInputDto;
import com.formacion.cuatroenraya.moves.infrastructure.controller.dto.MovesOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/moves")
@Slf4j
public class MovesController {
    @Autowired
    MovesService movesService;

    //cambiar a moves controller

    // Crear histórico
    @PostMapping()
    public ResponseEntity<Mono<MovesOutputDto>> createMove(@RequestBody MovesInputDto movesInputDto) {
        return new ResponseEntity<>(movesService.createMove(movesInputDto), HttpStatus.CREATED);
    }

    // Obtener histórico concreto por su id
    @GetMapping("/historico/{id}")
    public ResponseEntity<Mono<MovesOutputDto>> getMoveById(@PathVariable Integer id) {
        return new ResponseEntity<>(movesService.getMoveById(id), HttpStatus.OK);
    }

    // Obtener lista de todos los históricos guardados
    @GetMapping("/historial")
    public ResponseEntity<Flux<MovesOutputDto>> getAllMoves() {
        return new ResponseEntity<>(movesService.getAllMoves(), HttpStatus.OK);
    }

}
