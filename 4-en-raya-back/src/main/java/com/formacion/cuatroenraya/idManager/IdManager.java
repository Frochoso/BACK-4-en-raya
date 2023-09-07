package com.formacion.cuatroenraya.idManager;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class IdManager {
    private Integer playerId;
    private Integer gameId;
    private Integer column;
    private Integer row;
}
