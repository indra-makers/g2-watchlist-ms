package com.co.indra.coinmarketcap.watchlist.Config;

public enum ErrorCodes {

    WATCHLIST_WITH_NAME_EXISTS("Name of the Watchlist already exist", "001"),

    WATCHLIST_WITH_ID_NOT_EXISTS("Watchlist with that id not exists", "001"),

    WATCHLIST_WITH_SYMBOL_COIN_NOT_EXISTS("Watchlist with that symbol coin not exists", "001"),
    COIN_NAME_IN_WATCHLIST_ALREADY_EXIST("Name of coin in watchlist already exists", "002");


    String message;
    String code;
    ErrorCodes(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

}



