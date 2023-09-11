package com.formacion.cuatroenraya.records.application;

import com.formacion.cuatroenraya.records.infrastructure.controller.dto.RecordsInputDto;
import com.formacion.cuatroenraya.records.infrastructure.controller.dto.RecordsOutputDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecordsService {
    Mono<RecordsOutputDto> createRecord(RecordsInputDto recordsInputDto);
    Mono<RecordsOutputDto> getRecordById(Integer id);
    Flux<RecordsOutputDto> getAllRecords();
    Flux<RecordsOutputDto> findRecordsByGameId(Integer gameId);
}
