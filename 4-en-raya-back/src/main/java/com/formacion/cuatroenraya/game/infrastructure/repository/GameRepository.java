package com.formacion.cuatroenraya.game.infrastructure.repository;

import com.formacion.cuatroenraya.game.domain.Game;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface GameRepository extends ReactiveCrudRepository<Game, Integer> {

    @Query("SELECT p.player_id" +
            " FROM player p" +
            " JOIN records r ON r.player_id = p.id" +
            " WHERE r.game_id = :gameId" +
            " ORDER BY r.record_date DESC" +
            " LIMIT 1")
    Mono<Integer> findLastMove(Integer gameId);

    @Query("SELECT g.* FROM game g WHERE g.player2_id IS NULL")
    Flux<Game> findAllOnlyOnePlayer();
}
