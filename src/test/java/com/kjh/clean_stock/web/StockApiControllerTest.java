package com.kjh.clean_stock.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.portfolio.PortfolioRepository;
import com.kjh.clean_stock.domain.stock.StockRepository;
import com.kjh.clean_stock.web.dto.Portfolio.PortfolioSaveRequestDto;
import com.kjh.clean_stock.web.dto.Stock.StockApiSearchDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockApiControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setup(){
        mvc= MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StockRepository stockRepository;

    @After
    public void tearDown() throws Exception{
        stockRepository.deleteAll();
    }

//    @Test
//    @WithMockUser(roles="USER")
//    public void 주식_검색(){
//        String name ="삼성전자";
//        String ticker="005635";
//        String url = "http://localhost:"+port+"/api/v1/stock/search";
//        StockApiSearchDto requestDto = StockApiSearchDto.builder()
//                .name(name)
//                .ticker(ticker)
//                .build();
//        try {
//            mvc.perform(post(url)
//                    .contentType(MediaType.APPLICATION_JSON_UTF8)
//                    .content(new ObjectMapper().writeValueAsString(requestDto)))
//                    .andExpect(status().isOk());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
