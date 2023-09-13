package com.formacion.cuatroenraya.game.domain;

import com.formacion.cuatroenraya.player.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("game")
public class Game {

    @Id
    private Integer id;

    @Column("player1_id")
    private Integer player1Id;

    @Column("player2_id")
    private Integer player2Id;

    private Integer[][] size;

    private Integer winner;
}
