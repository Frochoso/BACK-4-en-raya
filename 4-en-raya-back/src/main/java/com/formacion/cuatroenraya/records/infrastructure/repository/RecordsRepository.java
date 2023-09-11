package com.formacion.cuatroenraya.records.infrastructure.repository;

import com.formacion.cuatroenraya.records.domain.Record;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RecordsRepository extends ReactiveCrudRepository<Record, Integer> {
    @Query("select r.* from records r where r.game_id=:id")
    Flux<Record> findRecordsByGameId(Integer id);
}
