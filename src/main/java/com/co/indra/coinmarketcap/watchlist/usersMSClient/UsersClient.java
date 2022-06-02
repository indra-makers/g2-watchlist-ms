package com.co.indra.coinmarketcap.watchlist.usersMSClient;

import com.co.indra.coinmarketcap.watchlist.Config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.models.Response.ErrorResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsersClient {

    private final RestTemplate restTemplate;

    public UsersClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public Users findUserByUsername(String username) {
        String url ="https://g2-users-ms.herokuapp.com/api/users-ms/users/"+username;
        return this.restTemplate.getForObject(url, Users.class);
    }
}
