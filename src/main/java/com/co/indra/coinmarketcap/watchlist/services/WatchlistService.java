package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.Config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.excepciones.BussinessException;
import com.co.indra.coinmarketcap.watchlist.excepciones.NotFoundException;
import com.co.indra.coinmarketcap.watchlist.messaging.AlertsProducer;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Alerts;
import com.co.indra.coinmarketcap.watchlist.models.Entities.CoinWatchlist;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import com.co.indra.coinmarketcap.watchlist.models.Response.Notification;
import com.co.indra.coinmarketcap.watchlist.repositories.CoinWatchlistRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlertsProducer alertsProducer;

    /*public void createWatchlist(Watchlist watchlist){
        if(watchlistRepository.findByUsernameAndName(watchlist.getUsername(), watchlist.getNameWatchlist()).size()==0){
            watchlistRepository.create(watchlist);
        }else{
            throw new BussinessException(ErrorCodes.WATCHLIST_WITH_NAME_EXISTS);
        }
    }*/

    public void createWatchlist(Watchlist watchlist) {
        if (usersApiClient.findUserByUsername(watchlist.getUsername()).getUsername().equals(watchlist.getUsername())){
            if(watchlistRepository.findByUsernameAndName(watchlist.getUsername(), watchlist.getNameWatchlist()).size()==0){
                watchlistRepository.create(watchlist);
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

    public void sendNotification(String idSymbolCoin, long price) {
        List<CoinWatchlist> listCoinWatchlist = coinWatchlistRepository.findCoinWatchlistsByIdSymbolCoin(idSymbolCoin);
        List<Alerts> listAlerts = userRepository.findAlertByIdSymbolCoin(idSymbolCoin, "true");
        if (listCoinWatchlist.isEmpty()) {
            throw new NotFoundException(ErrorCodes.COIN_DOESNOT_EXIST.getMessage());
        }
        if (listAlerts.isEmpty()) {
            throw new NotFoundException(ErrorCodes.COIN_DOESNOT_ALERT.getMessage());
        }
        System.out.println(listAlerts.size());
        for (int c = 0; c < listAlerts.size(); c++) {
            double min = listAlerts.get(c).getPrice() - (listAlerts.get(c).getPrice() * 0.002);
            double max = listAlerts.get(c).getPrice() + (listAlerts.get(c).getPrice() * 0.002);
            System.out.println(min + max);
            if (price> max || price <min) {
                Notification notification = new Notification(listAlerts.get(c).getUsername(), "SMS", "Alerta", "El precio esta +/- 2%");
                alertsProducer.sendNotification(notification);
            }
        }
    }
}
