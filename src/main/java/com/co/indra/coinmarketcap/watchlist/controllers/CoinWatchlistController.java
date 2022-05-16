package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.models.Entities.CoinWatchlist;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import com.co.indra.coinmarketcap.watchlist.services.CoinWatchlistService;
import com.co.indra.coinmarketcap.watchlist.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/coin-watchlists")
public class CoinWatchlistController {

    @Autowired
    private CoinWatchlistService coinWatchlistService;


    /**
     * URL /coin-watchlists
     * @param coinWatchlist
     */
    @PostMapping
    public void create(@Valid @RequestBody CoinWatchlist coinWatchlist){
        coinWatchlistService.createCoinWatchlist(coinWatchlist);
    }


}
