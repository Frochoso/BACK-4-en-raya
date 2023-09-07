package com.formacion.cuatroenraya.player.domain;

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
@Table("jugador")
public class Player {

    @Id
    @Column("id")
    private Integer id;

    @Column("player_name")
    private String playerName;
}
