package com.co.indra.coinmarketcap.watchlist.excepciones;

import com.co.indra.coinmarketcap.watchlist.Config.ErrorCodes;

public class BussinessException extends RuntimeException {


    private ErrorCodes code;

        public BussinessException(String message) {
            super(message);
        }

        public BussinessException(ErrorCodes code) {
            super(code.getMessage());
            this.code = code;
        }

        public String getCode() {
            return code.getCode();
        }
}
