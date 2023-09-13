package com.formacion.cuatroenraya.records.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("records")
public class Record {
    @Id
    @Column("id")
    private Integer id;

    @Column("record_date")
    private LocalDate recordDate;
    @Column("record_row")
    private Integer recordRow;
    @Column("record_column")
    private Integer recordColumn;
    @Column("move_number")
    private Integer moveNumber;
    @Column("player_id")
    private Integer playerId;
    @Column("game_id")
    private Integer gameId;
}
