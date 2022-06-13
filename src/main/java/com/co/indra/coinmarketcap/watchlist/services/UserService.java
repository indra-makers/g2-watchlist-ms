package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.APIs.clients.UsersApiClient;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Alerts;
import com.co.indra.coinmarketcap.watchlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsersApiClient usersApiClient;


    public void createAlert(String username, Alerts alerts){
        usersApiClient.getUserFromUsersmsByUsername(username);
        userRepository.create(username, alerts);
    }
}

