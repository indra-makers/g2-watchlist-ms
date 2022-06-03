package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.Config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.excepciones.BussinessException;
import com.co.indra.coinmarketcap.watchlist.excepciones.NotFoundException;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import com.co.indra.coinmarketcap.watchlist.repositories.CoinWatchlistRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchlistRepository;
import com.co.indra.coinmarketcap.watchlist.APIs.clients.UsersApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private CoinWatchlistRepository coinWatchlistRepository;

    @Autowired
    private UsersApiClient usersApiClient;

    /*public void createWatchlist(Watchlist watchlist){
        if(watchlistRepository.findByUsernameAndName(watchlist.getUsername(), watchlist.getNameWatchlist()).size()==0){
            watchlistRepository.create(watchlist);
        }else{
            throw new BussinessException(ErrorCodes.WATCHLIST_WITH_NAME_EXISTS);
        }
    }*/

    public void createWatchlist(String username, Watchlist watchlist) {
        if (usersApiClient.findUserByUsername(username).getUsername().equals(username)) {
            if(watchlistRepository.findByUsernameAndName(username, watchlist.getNameWatchlist()).size()==0){
                watchlistRepository.create(watchlist, username);
            }else{
                throw new BussinessException(ErrorCodes.WATCHLIST_WITH_NAME_EXISTS);
            }
        }
        else{
            throw new NotFoundException(ErrorCodes.WATCHLIST_USER_DOESNOT_EXISTS.getMessage());
        }
    }

    public List<Watchlist> getWatchlistByUsername(String username) {
        if(watchlistRepository.findByUsername(username).isEmpty()){
            throw new NotFoundException(ErrorCodes.WATCHLIST_USER_DOESNOT_EXISTS.getMessage());
        }
        return watchlistRepository.findByUsername(username);
    }

}
