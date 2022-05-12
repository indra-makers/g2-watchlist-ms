package com.co.indra.coinmarketcap.watchlist.excepciones;

public class BussinessException extends RuntimeException {


    private String code;
    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(String message, String code) {
        super();
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
