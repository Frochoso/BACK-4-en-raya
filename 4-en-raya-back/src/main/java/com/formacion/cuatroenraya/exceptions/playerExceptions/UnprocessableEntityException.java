package com.formacion.cuatroenraya.exceptions.playerExceptions;


public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException(String message) {
        super(message);
    }
    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
