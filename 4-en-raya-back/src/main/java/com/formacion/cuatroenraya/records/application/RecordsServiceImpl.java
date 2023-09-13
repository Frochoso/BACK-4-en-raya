package com.formacion.cuatroenraya.records.application;

import com.formacion.cuatroenraya.exceptions.playerExceptions.EntityNotFoundException;
import com.formacion.cuatroenraya.records.domain.Record;
import com.formacion.cuatroenraya.records.infrastructure.controller.dto.RecordsOutputDto;
import com.formacion.cuatroenraya.records.infrastructure.repository.RecordsRepository;
import com.formacion.cuatroenraya.records.mapper.RecordsMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Slf4j
public class RecordsServiceImpl implements RecordsService {
    @Autowired
    RecordsRepository recordsRepository;
    RecordsMapper recordMapper = Mappers.getMapper(RecordsMapper.class);

    @Override
    public Mono<RecordsOutputDto> getRecordById(Integer id) {

        Mono<Record> recordMono = recordsRepository.findById(id);

        return recordMono
                .map(recordResult -> recordMapper.recordsToRecordsOutputDto(recordResult))
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Record not found")));
    }

    @Override
    public Flux<RecordsOutputDto> getAllRecords() {

        Flux<Record> recordsFlux = recordsRepository.findAll();

        return recordsFlux
                .map(recordResult -> recordMapper.recordsToRecordsOutputDto(recordResult));
    }

    @Override
    public Flux<RecordsOutputDto> findRecordsByGameId(Integer gameId) {

        Flux<Record> recordsFlux = recordsRepository.findRecordsByGameId(gameId);

        return recordsFlux
                .map(recordResult -> recordMapper.recordsToRecordsOutputDto(recordResult))
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Game not found")));
    }

    // Guarda en base de datos el movimiento
    public Mono<Void> createMove(Integer gameId, Integer playerId, Integer row, Integer column) {

        Record move = new Record();
        move.setRecordDate(LocalDate.now());
        move.setRecordColumn(column);
        move.setRecordRow(row);
        move.setMoveNumber(1);
        move.setPlayerId(playerId);
        move.setGameId(gameId);

        return recordsRepository.save(move).then();
    }
}
