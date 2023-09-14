package com.formacion.cuatroenraya.exceptions.playerExceptions;

public class BadRequestException  extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
