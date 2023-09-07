package com.formacion.cuatroenraya.game.infrastructure.repository;

import com.formacion.cuatroenraya.game.domain.Game;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface GameRepository extends ReactiveCrudRepository<Game, Integer> {

    @Query("SELECT player.playerNumber" +
            " FROM player p" +
            " JOIN moves m ON m.playerId = p.id" +
            " WHERE m.gameId = :gameId" +
            " ORDER BY m.date DESC" +
            " LIMIT 1")
    Mono<Integer> findLastMove(Integer idGame);

}
