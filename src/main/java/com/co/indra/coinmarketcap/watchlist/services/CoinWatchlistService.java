package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.Config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.excepciones.BussinessException;
import com.co.indra.coinmarketcap.watchlist.excepciones.NotFoundException;
import com.co.indra.coinmarketcap.watchlist.models.Entities.CoinWatchlist;
import com.co.indra.coinmarketcap.watchlist.repositories.CoinWatchlistRepository;
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
}
