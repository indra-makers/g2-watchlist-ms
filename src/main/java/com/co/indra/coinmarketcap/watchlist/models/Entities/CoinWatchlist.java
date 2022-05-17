package com.co.indra.coinmarketcap.watchlist.models.Entities;

public class CoinWatchlist {

    private int id;
    private String idSymbolCoin;
    private int idWatchlist;

    public CoinWatchlist(){

    }

    public CoinWatchlist(int id, String idSymbolCoin, int idWatchlist) {
        this.id=id;
        this.idSymbolCoin = idSymbolCoin;
        this.idWatchlist = idWatchlist;
    }

    public CoinWatchlist(String idSymbolCoin, int idWatchlist) {
        this.id=id;
        this.idSymbolCoin = idSymbolCoin;
        this.idWatchlist = idWatchlist;
    }

    public String getIdSymbolCoin() {
        return idSymbolCoin;
    }

    public void setIdSymbolCoin(String idSymbolCoin) {
        this.idSymbolCoin = idSymbolCoin;
    }

    public int getIdWatchlist() {
        return idWatchlist;
    }

    public void setIdWatchlist(int idWatchlist) {
        this.idWatchlist = idWatchlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
