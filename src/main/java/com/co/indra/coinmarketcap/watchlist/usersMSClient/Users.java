package com.co.indra.coinmarketcap.watchlist.usersMSClient;

public class Users {

    private String username;

    private String displayName;

    private Integer idCategoryUser;

    private String mail;

    public Users() {
    }

    public Users(String username, String displayName, Integer idCategoryUser, String mail) {
        this.username = username;
        this.displayName = displayName;
        this.idCategoryUser = idCategoryUser;
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getIdCategoryUser() {
        return idCategoryUser;
    }

    public void setIdCategoryUser(Integer idCategoryUser) {
        this.idCategoryUser = idCategoryUser;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
