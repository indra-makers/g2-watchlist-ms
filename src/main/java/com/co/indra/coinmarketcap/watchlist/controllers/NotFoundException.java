package com.co.indra.coinmarketcap.watchlist.controllers;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
