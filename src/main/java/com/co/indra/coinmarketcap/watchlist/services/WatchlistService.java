package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.Config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.excepciones.BussinessException;
import com.co.indra.coinmarketcap.watchlist.excepciones.NotFoundException;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import com.co.indra.coinmarketcap.watchlist.repositories.CoinWatchlistRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private CoinWatchlistRepository coinWatchlistRepository;

    public void createWatchlist(Watchlist watchlist){
        if(watchlistRepository.findByUsernameAndName(watchlist.getUsername(), watchlist.getNameWatchlist()).size()==0){
            watchlistRepository.create(watchlist);
        }else{
            throw new BussinessException(ErrorCodes.WATCHLIST_WITH_NAME_EXISTS);
        }
    }

    public List<Watchlist> getWatchlistByUsername(String username) {
        return watchlistRepository.findByUsername(username);
    }

    /**
     * Delete watchlist
     * na watchlist with repeated idWatchlist.
     * @param idSymbolCoin,idWatchlist
     */
    public void deleteWatchlist( String idSymbolCoin, int idWatchlist ) {

        if(watchlistRepository.findByIdWatchlist(idWatchlist).isEmpty()){
            throw new NotFoundException(ErrorCodes.WATCHLIST_WITH_ID_NOT_EXISTS.getMessage());
        }else if(coinWatchlistRepository.findByIdSymbolCoin(idSymbolCoin, idWatchlist).isEmpty()){
            throw new NotFoundException(ErrorCodes.WATCHLIST_WITH_SYMBOL_COIN_NOT_EXISTS.getMessage());
        }else{
           coinWatchlistRepository.deleteCoinWatchlist(idSymbolCoin, idWatchlist);
           watchlistRepository.deleteWatchlist(idWatchlist);
        }
    }

}
