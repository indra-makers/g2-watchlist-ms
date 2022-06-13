package com.co.indra.coinmarketcap.watchlist.repositories;

import com.co.indra.coinmarketcap.watchlist.models.Entities.Alerts;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(String username, Alerts alerts){
        jdbcTemplate.update("INSERT INTO tbl_alerts(id_symbol_coin, price, username, notified) values(?,?,?,?)",
                alerts.getIdSymbolCoin(), alerts.getPrice(), username, alerts.getNotified());
    }
}
