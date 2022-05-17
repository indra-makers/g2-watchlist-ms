package com.co.indra.coinmarketcap.watchlist.excepciones;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}
