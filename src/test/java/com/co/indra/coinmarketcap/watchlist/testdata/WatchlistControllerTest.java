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
public class WatchlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private CoinWatchlistRepository coinWatchlistRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void createWatchlistHappyPath() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.WATCHLIST_PATH)
                .content("{\n" +
                        "    \"username\": \"abuitragogo\",\n" +
                        "    \"nameWatchlist\": \"nameW\",\n" +
                        "    \"visibility\": \"true\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        List<Watchlist> watchlists = watchlistRepository.findByUsernameAndName("abuitragogo", "nameW");
        Assertions.assertEquals(1, watchlists.size());

        Watchlist watchlistToAssert = watchlists.get(0);

        Assertions.assertEquals("nameW", watchlistToAssert.getNameWatchlist());
        Assertions.assertEquals("abuitragogo", watchlistToAssert.getUsername());
        Assertions.assertEquals("true", watchlistToAssert.isVisibility());
    }

    @Test
    public void createWatchlistWitchAlreadyNameExist() throws Exception {
        //----la preparacion de los datos de prueba-------
        watchlistRepository.create(new Watchlist("abuitragogo", "name Watchlist test", "true"));

        //----la ejecucion de la prueba misma--------------
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/watchlist")
                .content("{\n" +
                        "    \"username\": \"abuitragogo\",\n" +
                        "    \"nameWatchlist\": \"name Watchlist test\",\n" +
                        "    \"visibility\": \"true\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //------------ las verificaciones--------------------
        Assertions.assertEquals(412, response.getStatus());

        String textResponse = response.getContentAsString();
        System.out.println(textResponse);

        String textREsponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textREsponse, ErrorResponse.class);

        Assertions.assertEquals("001", error.getCode());
        Assertions.assertEquals("Name of the Watchlist already exist", error.getMessage());
    }


    /**@Test
    @Sql("/testdata/get_watchlist.sql")
    public void deleteCoinWatchlistHappyPath() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/31/coins?BTC")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

    }**/
    @Test
    @Sql("/testdata/get_watchlist.sql")
    public void deleteWatchlistIdWatchlistBadPath() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/33/coins/BTC")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());

        List<Object[]> resulst = jdbcTemplate.query("SELECT id_watchlist, username, name_watchlist, visibility FROM tbl_watchlist WHERE id_watchlist=?",
                (rs, rn) -> {
                    return new Object[] {rs.getObject("id_watchlist")};
                },
                2000);

        Assertions.assertEquals(0, resulst.size());

    }

    @Test
    @Sql("/testdata/get_watchlist.sql")
    public void deleteWatchlistCoinBadPath() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/33/coins/BTA")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());
    }

}
