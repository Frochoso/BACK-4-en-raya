package com.formacion.cuatroenraya.moves.infrastructure.repository;

import com.formacion.cuatroenraya.moves.domain.Moves;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovesRepository extends ReactiveCrudRepository<Moves, Integer> {
}
