package com.formacion.cuatroenraya.player.infrastructure.repository;

import com.formacion.cuatroenraya.player.domain.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PlayerRepository extends ReactiveCrudRepository<Player, Integer> {
    Mono<Player> findPlayerByPlayerName(String playerName);
}
