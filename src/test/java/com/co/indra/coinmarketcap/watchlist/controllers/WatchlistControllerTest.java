package com.co.indra.coinmarketcap.watchlist.controllers;

import com.co.indra.coinmarketcap.watchlist.APIs.models.Users;
import com.co.indra.coinmarketcap.watchlist.Config.Routes;
import com.co.indra.coinmarketcap.watchlist.models.Entities.Watchlist;
import com.co.indra.coinmarketcap.watchlist.models.Response.ErrorResponse;
import com.co.indra.coinmarketcap.watchlist.repositories.CoinWatchlistRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchlistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

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

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void createWatchlistHappyPath() throws Exception {
        Users mockedBody = new Users("Username",
                        "user",
                        1,
                        "user@user.com");

        ResponseEntity<Users> entity = new ResponseEntity(mockedBody, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(
                Mockito.anyString(),
                Mockito.<Class<Users>>any()
        )).thenReturn(entity);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.WATCHLIST_PATH+Routes.ID_USER_PATH, "Username")
                .content("{\n" +
                        "    \"nameWatchlist\": \"nameW\",\n" +
                        "    \"visibility\": \"true\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);



        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        List<Watchlist> watchlists = watchlistRepository.findByUsernameAndName("Username", "nameW");
        Assertions.assertEquals(1, watchlists.size());

        Watchlist watchlistToAssert = watchlists.get(0);

        Assertions.assertEquals("nameW", watchlistToAssert.getNameWatchlist());
        Assertions.assertEquals("Username", watchlistToAssert.getUsername());
        Assertions.assertEquals("true", watchlistToAssert.isVisibility());
    }

    @Test
    public void createWatchlistByUsernameNotExists() throws Exception {
        Users mockedBody = new Users("Username",
                "user",
                1,
                "user@user.com");

        ResponseEntity<Users> entity = new ResponseEntity(mockedBody, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(
                Mockito.anyString(),
                Mockito.<Class<Users>>any()
        )).thenReturn(entity);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.WATCHLIST_PATH+Routes.ID_USER_PATH, "nobody")
                .content("{\n" +
                        "    \"nameWatchlist\": \"nameW\",\n" +
                        "    \"visibility\": \"true\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);



        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());

        String textResponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);
        Assertions.assertEquals("NOT FOUND", error.getCode());
    }

    @Test
    @Sql("/testdata/get_watchlist.sql")
    public void createWatchlistWitchAlreadyNameExist() throws Exception {
        Users mockedBody = new Users("Username",
                "user",
                1,
                "user@user.com");

        ResponseEntity<Users> entity = new ResponseEntity(mockedBody, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(
                Mockito.anyString(),
                Mockito.<Class<Users>>any()
        )).thenReturn(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.WATCHLIST_PATH+Routes.ID_USER_PATH, "Username")
                .content("{\n" +
                        "    \"nameWatchlist\": \"namwW\",\n" +
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

    @Test
    @Sql("/testdata/get_watchlist.sql")
    public void getWatchlistByUsername() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Routes.WATCHLIST_PATH+"?username=Username").contentType(
                        MediaType.APPLICATION_JSON
                );
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200,response.getStatus());
        Watchlist[] watchlist = objectMapper.readValue(response.getContentAsString(),Watchlist[].class);
        Assertions.assertEquals(1,watchlist.length);
    }

    @Test
    @Sql("/testdata/get_watchlist.sql")
    public void getWatchlistByUsernameNoExists() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Routes.WATCHLIST_PATH+"?username=aquiles").contentType(
                        MediaType.APPLICATION_JSON
                );
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404,response.getStatus());
        ErrorResponse error = objectMapper.readValue(response.getContentAsString(),ErrorResponse.class);
        Assertions.assertEquals("User does not exists",error.getMessage());
    }



}
