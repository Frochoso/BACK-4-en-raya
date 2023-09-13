package com.formacion.cuatroenraya.records.mapper;

import com.formacion.cuatroenraya.records.domain.Record;
import com.formacion.cuatroenraya.records.infrastructure.controller.dto.RecordsInputDto;
import com.formacion.cuatroenraya.records.infrastructure.controller.dto.RecordsOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RecordsMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "recordDate", source = "recordDate")
    @Mapping(target = "recordRow", source = "recordRow")
    @Mapping(target = "recordColumn", source = "recordColumn")
    @Mapping(target = "playerId", source = "playerId")
    @Mapping(target = "gameId", source = "gameId")
    Record recordsInputDtoToRecords(RecordsInputDto recordsInputDto);

    RecordsOutputDto recordsToRecordsOutputDto(Record record);
}