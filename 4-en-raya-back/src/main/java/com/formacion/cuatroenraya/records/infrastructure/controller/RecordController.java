package com.formacion.cuatroenraya.records.infrastructure.controller;

import com.formacion.cuatroenraya.records.application.RecordsService;
import com.formacion.cuatroenraya.records.infrastructure.controller.dto.RecordsInputDto;
import com.formacion.cuatroenraya.records.infrastructure.controller.dto.RecordsOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/records")
@Slf4j
public class RecordController {
    @Autowired
    RecordsService recordsService;

    // Obtener histórico concreto por su id
    @GetMapping("/{id}")
    public ResponseEntity<Mono<RecordsOutputDto>> getMoveById(@PathVariable Integer id) {
        return new ResponseEntity<>(recordsService.getRecordById(id), HttpStatus.OK);
    }

    // Obtener lista de todos los históricos guardados
    @GetMapping("/list")
    public ResponseEntity<Flux<RecordsOutputDto>> getAllMoves() {
        return new ResponseEntity<>(recordsService.getAllRecords(), HttpStatus.OK);
    }

    // Obtener todos los históricos de un tablero por su id
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Flux<RecordsOutputDto>> findHistoricByGameId(@PathVariable Integer gameId) {
        return new ResponseEntity<>(recordsService.findRecordsByGameId(gameId), HttpStatus.OK);
    }

}
