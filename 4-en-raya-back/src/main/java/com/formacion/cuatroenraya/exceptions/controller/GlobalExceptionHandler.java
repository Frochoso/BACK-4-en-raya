package com.formacion.cuatroenraya.exceptions.controller;

import com.formacion.cuatroenraya.exceptions.error.CustomError;
import com.formacion.cuatroenraya.exceptions.playerExceptions.BadRequestException;
import com.formacion.cuatroenraya.exceptions.playerExceptions.NoContentException;
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
        CustomError customError = new CustomError(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return Mono.just(customError);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public Mono<CustomError> handleUnprocessableEntityException(UnprocessableEntityException ex) {
        CustomError customError = new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        return Mono.just(customError);
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public Mono<CustomError> handleNoContentException(NoContentException ex) {
        CustomError customError = new CustomError(new Date(), HttpStatus.NO_CONTENT.value(), ex.getMessage());
        return Mono.just(customError);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<CustomError> handleBadRequestException(BadRequestException ex) {
        CustomError customError = new CustomError(new Date(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return Mono.just(customError);
    }
}