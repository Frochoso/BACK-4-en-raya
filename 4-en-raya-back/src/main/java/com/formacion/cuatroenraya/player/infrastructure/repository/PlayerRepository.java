package com.formacion.cuatroenraya.player.infrastructure.repository;

import com.formacion.cuatroenraya.player.domain.Player;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PlayerRepository extends ReactiveCrudRepository<Player, Integer> {
    Mono<Player> findPlayerByPlayerName(String playerName);

    @Query("SELECT p.* FROM player p, game g WHERE p.playerNumer = :playerId" +
            " AND g.id = :gameId AND p.id = g.player1_id OR p.id = g.player2_id LIMIT 1")
    Mono<Player> findPlayerByGameIdAndPlayerId(Integer gameId, Integer playerId);

}
