package com.formacion.cuatroenraya.game.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("game")
public class Game {

    @Id
    private Integer id;

    private Integer player1_id;

    private Integer player2_id;

    private Integer[][] size;
}
