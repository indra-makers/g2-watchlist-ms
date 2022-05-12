package com.co.indra.coinmarketcap.watchlist.repositories;

import com.co.indra.coinmarketcap.watchlist.models.Entities.CoinWatchlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class CoinWatchlistRowMapper implements RowMapper<CoinWatchlist> {
    @Override
    public CoinWatchlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        CoinWatchlist coinWatchlist = new CoinWatchlist();
        coinWatchlist.setId(rs.getInt("id"));
        coinWatchlist.setIdSymbolCoin(rs.getString("idSymbolCoin"));
        coinWatchlist.setIdWatchlist(rs.getInt("idWatchlist"));
        return coinWatchlist;
    }
}

@Repository
public class CoinWatchlistRepository {

    private JdbcTemplate jdbcTemplate;

    public void create(CoinWatchlist coinWatchlist){
        jdbcTemplate.update("INSERT INTO tbl_coinWatchlist(idSymbolCoin, idWatchlist) values(?,?)",
                coinWatchlist.getIdSymbolCoin(), coinWatchlist.getIdWatchlist());
    }

    public List<CoinWatchlist> findBy(String username) {
        return jdbcTemplate.query(
                "SELECT idWatchlist, username, nameWatchlist, visibility FROM tbl_watchlist WHERE username=?",
                new CoinWatchlistRowMapper(),
                username);
    }

}
