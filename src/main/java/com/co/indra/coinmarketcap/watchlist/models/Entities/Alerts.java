package com.co.indra.coinmarketcap.watchlist.models.Entities;

public class Alerts {

    private int idAlert;
    private String idSymbolCoin;
    private Long price;
    private String username;
    private String notified;

    public Alerts() {
    }

    public Alerts(String idSymbolCoin, Long price, String username, String notified) {
        this.idSymbolCoin = idSymbolCoin;
        this.price = price;
        this.username = username;
        this.notified = notified;
    }
    public Alerts(String idSymbolCoin, Long price, String notified) {
        this.idSymbolCoin = idSymbolCoin;
        this.price = price;
        this.notified = notified;
    }

    public int getIdAlert() {
        return idAlert;
    }

    public void setIdAlert(int idAlert) {
        this.idAlert = idAlert;
    }

    public String getIdSymbolCoin() {
        return idSymbolCoin;
    }

    public void setIdSymbolCoin(String idSymbolCoin) {
        this.idSymbolCoin = idSymbolCoin;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotified() {
        return notified;
    }

    public void setNotified(String notified) {
        this.notified = notified;
    }
}
