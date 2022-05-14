package com.co.indra.coinmarketcap.watchlist.models.Entities;

public class Watchlist {

    private int idWatchlist;
    private String username;
    private String nameWatchlist;
    private String visibility;

    public Watchlist(){
    }

    public Watchlist(int idWatchlist, String username, String nameWatchlist, String visibility) {
        this.username = username;
        this.nameWatchlist = nameWatchlist;
        this.visibility = visibility;
    }

    public int getIdWatchlist() {
        return idWatchlist;
    }

    public void setIdWatchlist(int idWatchlist) {
        this.idWatchlist = idWatchlist;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameWatchlist() {
        return nameWatchlist;
    }

    public void setNameWatchlist(String nameWatchlist) {
        this.nameWatchlist = nameWatchlist;
    }

    public String isVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
