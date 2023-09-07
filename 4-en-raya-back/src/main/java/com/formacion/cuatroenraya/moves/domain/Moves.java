package com.formacion.cuatroenraya.moves.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("moves")
public class Moves {
    @Id
    private Integer id;

    @Column("date")
    private LocalDateTime date;
    @Column("row")
    private Integer row;
    @Column("column")
    private Integer column;
    @Column("move_number")
    private Integer moveNumber;
    @Column("player_id")
    private Integer playerId;
    @Column("game_id")
    private Integer gameId;
}
