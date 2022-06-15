package com.co.indra.coinmarketcap.watchlist.APIs.clients;

import com.co.indra.coinmarketcap.watchlist.APIs.models.Users;
import com.co.indra.coinmarketcap.watchlist.Config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.Config.RestTemplateConfig;
import com.co.indra.coinmarketcap.watchlist.excepciones.BussinessException;
import com.co.indra.coinmarketcap.watchlist.excepciones.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UsersApiClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.users.url}")
    private String apiUrl;

    @Cacheable(value = "watchlistByUsername", cacheManager = "expire30Mins", key = "#username", unless = "#result == null")
    public Users findUserByUsername(String username) {
        UriComponentsBuilder uri = UriComponentsBuilder
                .fromUriString(apiUrl+username);

        ResponseEntity<Users> response = restTemplate.getForEntity(uri.toUriString(),
                Users.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new NotFoundException(ErrorCodes.WATCHLIST_USER_DOESNOT_EXISTS.getMessage());
        }

        Users body = response.getBody();
        return body;
    }

    public Users getUserFromUsersmsByUsername(String username) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromUriString(apiUrl).path(username);
        ResponseEntity<Users> responseUser = restTemplate.getForEntity(uri.toUriString(), Users.class);
        Users body = responseUser.getBody();
        return new Users(body.getUsername(), body.getDisplayName(), body.getIdCategoryUser(), body.getMail());
    }
}
