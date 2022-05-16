package com.co.indra.coinmarketcap.watchlist.repositories;

import com.co.indra.coinmarketcap.watchlist.models.Entities.CoinWatchlist;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(CoinWatchlist coinWatchlist){
        jdbcTemplate.update("INSERT INTO tbl_coinWatchlist(idSymbolCoin, id_watchlist) values(?,?)",
                coinWatchlist.getIdSymbolCoin(), coinWatchlist.getIdWatchlist());
    }

    public List<CoinWatchlist> findByUsernameAndSimboly(String username, String simbolyCoin){
        return jdbcTemplate.query(
                "SELECT id, id_symbolCoin, id_watchlist FROM tbl_coinWatchlist WHERE id_symbolCoin=? and id_watchlist=?",
                new CoinWatchlistRowMapper(),
                username, simbolyCoin);
    }

}
