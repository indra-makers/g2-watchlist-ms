package com.co.indra.coinmarketcap.watchlist.models.Request;

public class Price {
    private long price;

    public Price() {
    }

    public Price(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
