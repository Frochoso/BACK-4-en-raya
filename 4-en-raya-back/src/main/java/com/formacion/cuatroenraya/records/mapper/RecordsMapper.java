package com.formacion.cuatroenraya.records.mapper;

import com.formacion.cuatroenraya.records.domain.Record;
import com.formacion.cuatroenraya.records.infrastructure.controller.dto.RecordsInputDto;
import com.formacion.cuatroenraya.records.infrastructure.controller.dto.RecordsOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RecordsMapper {
    @Mapping(target="id", source="id")
    @Mapping(target="date", source="date")
    @Mapping(target="row", source="row")
    @Mapping(target="column", source="column")
    @Mapping(target="playerId", source="playerId")
    @Mapping(target="gameId", source="gameId")
    Record recordsInputDtoToRecords(RecordsInputDto recordsInputDto);

    @Mapping(target="id", source="id")
    @Mapping(target="date", source="date")
    @Mapping(target="row", source="row")
    @Mapping(target="column", source="column")
    @Mapping(target="moveNumber", source="moveNumber")
    @Mapping(target="playerId", source="playerId")
    @Mapping(target="gameId", source="gameId")
    RecordsOutputDto recordsToRecordsOutputDto(Record record);
}