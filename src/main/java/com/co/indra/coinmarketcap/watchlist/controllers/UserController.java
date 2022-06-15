package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.Config.Routes;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Alerts;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import com.co.indra.coinmarketcap.watchlist.services.UserService;
import com.co.indra.coinmarketcap.watchlist.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(Routes.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(Routes.ID_USER+Routes.ALERTS)
    public void create(@PathVariable("username") String username, @RequestBody Alerts alerts){
        userService.createAlert(username, alerts);
    }
}
