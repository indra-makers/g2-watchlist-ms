package com.co.indra.coinmarketcap.watchlist.APIs.service;

import com.co.indra.coinmarketcap.watchlist.APIs.clients.UsersApiClient;
import com.co.indra.coinmarketcap.watchlist.APIs.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UsersApiService {

    @Autowired
    UsersApiClient usersApiClient;

    public Users getUserByUsername(String username) throws IOException {
        return usersApiClient.getUserByUsername(username);
    }
}
