package com.formacion.cuatroenraya.exceptions.controller;

import com.formacion.cuatroenraya.exceptions.error.CustomError;
import com.formacion.cuatroenraya.exceptions.playerExceptions.EntityNotFoundException;
import com.formacion.cuatroenraya.exceptions.playerExceptions.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Mono<CustomError> handlePlayerNotFoundException(EntityNotFoundException ex) {
        CustomError customError = new CustomError(new Date(), HttpStatus.NOT_FOUND.value(), "Player not found");
        return Mono.just(customError);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public Mono<CustomError> handleUnprocessableEntityException(UnprocessableEntityException ex) {
        CustomError customError = new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Name field can not be null");
        return Mono.just(customError);
    }
}
