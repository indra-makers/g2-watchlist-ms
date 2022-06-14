package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.Config.Routes;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import com.co.indra.coinmarketcap.watchlist.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(Routes.WATCHLIST_PATH)
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;


    /**
     * URL /watchlists
     * @param watchlist
     */
    @PostMapping()
    public void create(@Valid @RequestBody Watchlist watchlist){
        watchlistService.createWatchlist(watchlist);
    }

    /**
     *   GET /watchlist?username={{username}},
     * @param username
     * @return
     */
    @GetMapping
    public List<Watchlist> getWatchlistByUsername(@RequestParam(name = "username") String username) {
        return watchlistService.getWatchlistByUsername(username);
    }

    @PostMapping(Routes.ID_SYMBOLCOIN_PATH)
    public void sendNotification(@PathParam("id_symbolCoin") String idSymbolCoin, @RequestBody Long price){
        watchlistService.sendNotification(idSymbolCoin, price);
    }

}
