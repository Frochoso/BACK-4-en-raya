package com.formacion.cuatroenraya.moves.application;

import com.formacion.cuatroenraya.moves.infrastructure.controller.dto.MovesInputDto;
import com.formacion.cuatroenraya.moves.infrastructure.controller.dto.MovesOutputDto;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovesService {
    Mono<MovesOutputDto> createMove(MovesInputDto movesInputDto);
    Mono<MovesOutputDto> getMoveById(@PathVariable Integer id);
    Flux<MovesOutputDto> getAllMoves();
}
