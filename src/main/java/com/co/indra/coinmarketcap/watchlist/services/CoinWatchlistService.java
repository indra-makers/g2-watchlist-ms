package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.Config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.excepciones.BussinessException;
import com.co.indra.coinmarketcap.watchlist.excepciones.NotFoundException;
import com.co.indra.coinmarketcap.watchlist.models.Entities.CoinWatchlist;
import com.co.indra.coinmarketcap.watchlist.repositories.CoinWatchlistRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinWatchlistService {

    @Autowired
    private CoinWatchlistRepository coinWatchlistRepository;

    public void createCoinWatchlist(CoinWatchlist coinWatchlist){
        if(coinWatchlistRepository.findByUsernameAndSimboly(coinWatchlist.getIdWatchlist(), coinWatchlist.getIdSymbolCoin()).size()==0){
            coinWatchlistRepository.create(coinWatchlist);
        }else{
            throw new BussinessException(ErrorCodes.COIN_NAME_IN_WATCHLIST_ALREADY_EXIST);
        }
    }

    /**
     * Delete watchlist
     * na watchlist with repeated idWatchlist.
     * @param idSymbolCoin,idWatchlist
     */
    public void deleteCoinWatchlist( String idSymbolCoin, int idWatchlist ) {

        if(coinWatchlistRepository.findByIdWatchlist(idWatchlist).isEmpty()){
            throw new NotFoundException(ErrorCodes.WATCHLIST_WITH_ID_NOT_EXISTS.getMessage());
        }
        if(coinWatchlistRepository.findByIdSymbolCoin(idSymbolCoin, idWatchlist).isEmpty()){
            throw new NotFoundException(ErrorCodes.WATCHLIST_WITH_SYMBOL_COIN_NOT_EXISTS.getMessage());
        }
        coinWatchlistRepository.deleteCoinWatchlist(idSymbolCoin, idWatchlist);

    }


}
