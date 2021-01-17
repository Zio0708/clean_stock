package com.kjh.clean_stock.domain.portfolio;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioRepositoryTest {
    @Autowired
    PortfolioRepository portfolioRepository;

    @After
    public void cleanup(){
        portfolioRepository.deleteAll();
    }

    @Test
    public void 포트폴리오_불러오기(){
        String name ="테스트_제목";
        portfolioRepository.save(Portfolio.builder()
                .name(name)
                .build());

        List<Portfolio> portfolioLists = portfolioRepository.findAll();

        Portfolio portfolio = portfolioLists.get(0);
        assertThat(portfolio.getName()).isEqualTo(name);
    }
}
