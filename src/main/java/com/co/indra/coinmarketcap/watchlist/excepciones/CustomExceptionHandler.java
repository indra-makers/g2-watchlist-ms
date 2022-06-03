package com.co.indra.coinmarketcap.watchlist.excepciones;
//clase de manejo de excepciones

import com.co.indra.coinmarketcap.watchlist.Config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.models.Response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice //el intercepta las peticiones que llegan y salen del servicio,
public class CustomExceptionHandler {

    //se crea metodo independiente con respuesta con cuerpo personalizado


    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody //que la respuesta va a ser personalizada.
    @ExceptionHandler(BussinessException.class)
    public ErrorResponse handleBusinessException(BussinessException exception) {
        return new ErrorResponse(exception.getCode(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse("NOT FOUND", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleNotFoundException(MethodArgumentNotValidException exception) {
        return new ErrorResponse("BAD_PARAMETERS", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ErrorResponse clientException(HttpClientErrorException.NotFound exception) {
        return new ErrorResponse("404", ErrorCodes.WATCHLIST_USER_DOESNOT_EXISTS.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception) {
        return new ErrorResponse("500", exception.getMessage());
    }

}
