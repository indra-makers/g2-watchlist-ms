package com.co.indra.coinmarketcap.watchlist.repositories;

import com.co.indra.coinmarketcap.watchlist.models.Entities.Alerts;
import com.co.indra.coinmarketcap.watchlist.models.Entities.CoinWatchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class AlertRowMapper implements RowMapper<Alerts> {
    @Override
    public Alerts mapRow(ResultSet rs, int rowNum) throws SQLException {
        Alerts alerts = new Alerts();
        alerts.setIdAlert(rs.getInt("id_alert"));
        alerts.setIdSymbolCoin(rs.getString("id_symbol_coin"));
        alerts.setPrice(rs.getLong("price"));
        alerts.setUsername(rs.getString("username"));
        alerts.setNotified(rs.getString("notified"));
        return alerts;
    }
}
@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(String username, Alerts alerts){
        jdbcTemplate.update("INSERT INTO tbl_alerts(id_symbol_coin, price, username, notified) values(?,?,?,?)",
                alerts.getIdSymbolCoin(), alerts.getPrice(), username, alerts.getNotified());
    }

    public List<Alerts> findCoinWithAlertByIdSymbolCoin(String idSymbolCoin, String notified) {
        return jdbcTemplate.query(
                "SELECT id_alert, id_symbol_coin, price, username, notified FROM tbl_alerts WHERE id_symbol_coin=? AND notified=?",
                new AlertRowMapper(),
                idSymbolCoin, notified);
    }
}
