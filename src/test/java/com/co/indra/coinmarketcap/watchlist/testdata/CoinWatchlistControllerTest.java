package com.co.indra.coinmarketcap.watchlist.testdata;

import com.co.indra.coinmarketcap.watchlist.Config.Routes;
import com.co.indra.coinmarketcap.watchlist.models.Entities.CoinWatchlist;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import com.co.indra.coinmarketcap.watchlist.models.Response.ErrorResponse;
import com.co.indra.coinmarketcap.watchlist.repositories.CoinWatchlistRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchlistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CoinWatchlistControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CoinWatchlistRepository coinWatchlistRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Sql("/testdata/get_watchlist.sql")
    public void createCoinWatchlistHappyPath() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.WATCHLIST_PATH+Routes.COIN_WATCHLIST_PATH)
                .content("{\n" +
                        "    \"idSymbolCoin\": \"NFT\",\n" +
                        "    \"idWatchlist\": \"30\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        List<CoinWatchlist> coinWatchlists = coinWatchlistRepository.findByUsernameAndSimboly(30,"NFT");
        Assertions.assertEquals(1, coinWatchlists.size());

        CoinWatchlist coinWatchlistToAssert = coinWatchlists.get(0);

        Assertions.assertEquals("NFT", coinWatchlistToAssert.getIdSymbolCoin());
        Assertions.assertEquals(30, coinWatchlistToAssert.getIdWatchlist());
    }

    @Test
    @Sql("/testdata/get_watchlist.sql")
    public void createCoinWatchlistWitchAlreadyCoinNameExist() throws Exception {
        //----la preparacion de los datos de prueba-------
        coinWatchlistRepository.create(new CoinWatchlist( "NFT-test", 30));

        //----la ejecucion de la prueba misma--------------
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.WATCHLIST_PATH+Routes.COIN_WATCHLIST_PATH)
                .content("{\n" +
                        "    \"idSymbolCoin\": \"NFT-test\",\n" +
                        "    \"idWatchlist\": \"30\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //------------ las verificaciones--------------------
        Assertions.assertEquals(412, response.getStatus());

        String textResponse = response.getContentAsString();
        System.out.println(textResponse);

        String textREsponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textREsponse, ErrorResponse.class);

        Assertions.assertEquals("002", error.getCode());
        Assertions.assertEquals("Name of coin in watchlist already exists", error.getMessage());
    }

    @Test
    @Sql("/testdata/get_watchlistcoin.sql")
    public void deleteCoinWatchlistHappyPath() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(Routes.WATCHLIST_PATH +Routes.COIN_WATCHLIST_PATH + Routes.ID_WATCHLIST_PATH + Routes.ID_SYMBOLCOIN_PATH, 30, "BTC");

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        // ------------ las verificaciones--------------------
        Assertions.assertEquals(200, response.getStatus());

    }
    @Test
    @Sql("/testdata/get_watchlistcoin.sql")
    public void deleteWatchlistIdWatchlistNotExist() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(Routes.WATCHLIST_PATH +Routes.COIN_WATCHLIST_PATH + Routes.ID_WATCHLIST_PATH + Routes.ID_SYMBOLCOIN_PATH, 31, "BTC");

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());
        String textREsponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textREsponse, ErrorResponse.class);

        Assertions.assertEquals("NOT_FOUND", error.getCode());
        Assertions.assertEquals("Watchlist with that id not exists", error.getMessage());
    }

    @Test
    @Sql("/testdata/get_watchlistcoin.sql")
    public void deleteWatchlistWatchlistCoinNotExist() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(Routes.WATCHLIST_PATH +Routes.COIN_WATCHLIST_PATH + Routes.ID_WATCHLIST_PATH + Routes.ID_SYMBOLCOIN_PATH, 30, "BTZ");

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());
        String textREsponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textREsponse, ErrorResponse.class);

        Assertions.assertEquals("NOT_FOUND", error.getCode());
        Assertions.assertEquals("Watchlist with that symbol coin not exists", error.getMessage());
    }


}
