package com.co.indra.coinmarketcap.watchlist.repositories;

import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


class WatchlistRowMapper implements RowMapper<Watchlist> {
    @Override
    public Watchlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        Watchlist watchlist = new Watchlist();
        watchlist.setIdWatchlist(rs.getInt("idWatchlist"));
        watchlist.setUsername(rs.getString("username"));
        watchlist.setNameWatchlist(rs.getString("nameWatchlist"));
        watchlist.setVisibility(rs.getBoolean("visibility"));
        return watchlist;
    }
}

@Repository
public class WatchlistRepository {

    private JdbcTemplate jdbcTemplate;

    public void create(Watchlist watchlist){
        jdbcTemplate.update("INSERT INTO tbl_watchlist(username, nameWatchlist, visibility) values(?,?,?)",
                watchlist.getUsername(), watchlist.getNameWatchlist(), watchlist.isVisibility());
    }

    public List<Watchlist> findBy(String username) {
        return jdbcTemplate.query(
                "SELECT idWatchlist, username, nameWatchlist, visibility FROM tbl_watchlist WHERE username=?",
                new WatchlistRowMapper(),
                username);
    }
}
