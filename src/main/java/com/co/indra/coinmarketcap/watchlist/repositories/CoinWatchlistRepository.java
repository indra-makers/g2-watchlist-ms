package com.co.indra.coinmarketcap.watchlist.repositories;

import com.co.indra.coinmarketcap.watchlist.models.Entities.CoinWatchlist;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
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
        coinWatchlist.setIdSymbolCoin(rs.getString("id_symbolCoin"));
        coinWatchlist.setIdWatchlist(rs.getInt("id_watchlist"));
        return coinWatchlist;
    }
}

@Repository
public class CoinWatchlistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(CoinWatchlist coinWatchlist){
        jdbcTemplate.update("INSERT INTO tbl_coinWatchlist(id_symbolCoin, id_watchlist) values(?,?)",
                coinWatchlist.getIdSymbolCoin(), coinWatchlist.getIdWatchlist());
    }

    public List<CoinWatchlist> findByUsernameAndSimboly(int idWatchlist, String simbolyCoin){
        return jdbcTemplate.query(
                "SELECT id, id_symbolCoin, id_watchlist FROM tbl_coinWatchlist WHERE id_symbolcoin=? and id_watchlist=?",
                new CoinWatchlistRowMapper(),
                simbolyCoin, idWatchlist);
    }

    public List<CoinWatchlist> findByIdWatchlist(int idWatchlist) {
        return jdbcTemplate.query(
                "SELECT id, id_symbolcoin, id_watchlist FROM tbl_coinwatchlist WHERE id_watchlist=?",
                new CoinWatchlistRowMapper(),
                idWatchlist);
    }

    public List<CoinWatchlist> findByIdSymbolCoin(String symbolCoin, int idWatchlist) {
        return jdbcTemplate.query(
                "SELECT id, id_symbolcoin, id_watchlist FROM tbl_coinwatchlist WHERE id_symbolcoin=? AND id_watchlist=?",
                new CoinWatchlistRowMapper(),
                symbolCoin, idWatchlist);
    }

    public void deleteCoinWatchlist(String symbolCoin, int idWatchlist ) {

        jdbcTemplate.update(
                "DELETE  FROM tbl_coinwatchlist WHERE id_symbolcoin=? AND id_watchlist=?",
                symbolCoin, idWatchlist );
    }

    public List<CoinWatchlist> findCoinWatchlistsByIdSymbolCoin(String idSymbolCoin) {
        return jdbcTemplate.query(
                "SELECT id, id_symbolCoin, id_watchlist FROM tbl_coinwatchlist WHERE id_symbolcoin=?",
                new CoinWatchlistRowMapper(),
                idSymbolCoin);
    }

}
