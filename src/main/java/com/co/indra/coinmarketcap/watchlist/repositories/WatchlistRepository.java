package com.co.indra.coinmarketcap.watchlist.repositories;

import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
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
        watchlist.setIdWatchlist(rs.getInt("id_watchlist"));
        watchlist.setUsername(rs.getString("username"));
        watchlist.setNameWatchlist(rs.getString("name_watchlist"));
        watchlist.setVisibility(rs.getString("visibility"));
        return watchlist;
    }
}

@Repository
public class WatchlistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Watchlist watchlist){
        jdbcTemplate.update("INSERT INTO tbl_watchlist(username, name_watchlist, visibility) values(?,?,?)",
                watchlist.getUsername(), watchlist.getNameWatchlist(), watchlist.isVisibility());
    }

    public List<Watchlist> findByUsernameAndName(String username, String nameWatchlist) {
        return jdbcTemplate.query(
                "SELECT id_watchlist, username, name_watchlist, visibility FROM tbl_watchlist WHERE username=? and name_watchlist=?",
                new WatchlistRowMapper(),
                username, nameWatchlist);
    }

}
