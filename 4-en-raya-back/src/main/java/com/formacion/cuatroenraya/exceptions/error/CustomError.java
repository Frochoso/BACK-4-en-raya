package com.formacion.cuatroenraya.exceptions.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomError {
    private Date timestamp;
    private int httpCode;
    private String message;
}
