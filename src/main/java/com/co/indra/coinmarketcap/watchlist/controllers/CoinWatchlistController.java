package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.Config.Routes;
import com.co.indra.coinmarketcap.watchlist.models.Entities.CoinWatchlist;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import com.co.indra.coinmarketcap.watchlist.services.CoinWatchlistService;
import com.co.indra.coinmarketcap.watchlist.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(Routes.WATCHLIST_PATH+Routes.COIN_WATCHLIST_PATH)
public class CoinWatchlistController {

    @Autowired
    private CoinWatchlistService coinWatchlistService;

    /**
     * URL /watchlist/coins
     * @param coinWatchlist
     */
    @PostMapping
    public void create(@Valid @RequestBody CoinWatchlist coinWatchlist){
        coinWatchlistService.createCoinWatchlist(coinWatchlist);
    }

}
