package com.kjh.clean_stock.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.portfolio.PortfolioRepository;
import com.kjh.clean_stock.web.dto.PortfolioSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PortfolioApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @After
    public void tearDown() throws Exception{
        portfolioRepository.deleteAll();
    }

    @Test
    public void Portfolio_등록() throws Exception{
        String name ="name";
        PortfolioSaveRequestDto requestDto = PortfolioSaveRequestDto.builder()
                .name(name)
                .build();

        String url = "http://localhost:"+port+"/portfolio";
        ResponseEntity<Long> responseEntity= restTemplate.
                postForEntity(url,requestDto,Long.class);

        List<Portfolio> all =portfolioRepository.findAll();

        assertThat(all.get(0).getName()).isEqualTo(name);
    }
}
