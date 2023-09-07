package com.formacion.cuatroenraya.moves.application;

import com.formacion.cuatroenraya.exceptions.playerExceptions.EntityNotFoundException;
import com.formacion.cuatroenraya.exceptions.playerExceptions.UnprocessableEntityException;
import com.formacion.cuatroenraya.moves.domain.Moves;
import com.formacion.cuatroenraya.moves.infrastructure.controller.dto.MovesInputDto;
import com.formacion.cuatroenraya.moves.infrastructure.controller.dto.MovesOutputDto;
import com.formacion.cuatroenraya.moves.infrastructure.repository.MovesRepository;
import com.formacion.cuatroenraya.moves.mapper.MovesMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MovesServiceImpl implements MovesService {
    @Autowired
    MovesRepository movesRepository;
    MovesMapper mapper = Mappers.getMapper(MovesMapper.class);
    @Override
    public Mono<MovesOutputDto> createMove(MovesInputDto movesInputDto) {

        if (movesInputDto.getGameId() == null && movesInputDto.getPlayerId() == null) {
            throw new UnprocessableEntityException("Game and Player fields must be filled");
        }

        Moves move = mapper.movesInputDtoToMoves(movesInputDto);

        Mono<Moves> moveMono = movesRepository.save(move);

        return moveMono.map(moveResult -> mapper.movesToMovesOutputDto(moveResult));
    }

    @Override
    public Mono<MovesOutputDto> getMoveById(Integer id) {

        Mono<Moves> moveMono = movesRepository.findById(id);

        return moveMono
                .map(moveResult -> mapper.movesToMovesOutputDto(moveResult))
                .switchIfEmpty(Mono.error(new EntityNotFoundException("History not found")));
    }

    @Override
    public Flux<MovesOutputDto> getAllMoves() {

        Flux<Moves> movesFlux = movesRepository.findAll();

        return movesFlux
                .map(movesResult -> mapper.movesToMovesOutputDto(movesResult));
    }

    public void createMove(Integer gameId, Integer playerId, Integer row, Integer column) {

        Moves move = new Moves();
        move.setDate(LocalDateTime.now());
        move.setColumn(column);
        move.setRow(row);
        move.setMoveNumber(1);
        move.setPlayerId(playerId);
        move.setGameId(gameId);

        movesRepository.save(move).subscribe();
    }


}
