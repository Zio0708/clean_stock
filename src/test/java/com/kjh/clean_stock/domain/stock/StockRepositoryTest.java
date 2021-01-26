package com.kjh.clean_stock.domain.stock;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.portfolio.PortfolioRepository;
import com.kjh.clean_stock.service.stock.StockService;
import com.kjh.clean_stock.web.dto.Stock.StockListResponseDto;
import com.kjh.clean_stock.web.dto.Stock.StockResponseDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockRepositoryTest {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    StockService stockService;

    @After
    public void cleanup(){
        stockRepository.deleteAll();
    }

//    @Test
//    public void 주식_단일_불러오기(){
//        String name ="테스트_제목";
//        String ticker ="테스트_티커";
//        BigDecimal price= new BigDecimal(100.01);
//        stockRepository.save(Stock.builder()
//                .name(name)
//                .ticker(ticker)
//                .price(price)
//                .build());
//
//        List<Stock> stockLists = stockRepository.findAll();
//
//        Stock stock = stockLists.get(0);
//        System.out.println(stock.getName()+stock.getTicker()+stock.getPrice());
//    }
//    @Test
//    public void KOSPI_불러오기(){
//
//        try {
//            stockService.saveKOSPI();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        List<Stock> stockLists = stockRepository.findAll();
//        Stock stock = stockLists.get(0);
//        System.out.println(stock.getName()+stock.getTicker()+stock.getPrice());
//    }
//    @Test
//    public void 주식_이름_검색(){
//        KOSPI_불러오기();
//        String name = "삼성전자";
//        List<StockListResponseDto> stockAry=stockService.findByName(name);
//        for(StockListResponseDto s : stockAry){
//            System.out.println(s.getName());
//        }
//    }
//    @Test
//    public void 주식_티커_검색(){
//        KOSPI_불러오기();
//        String ticker = "005930";
//        List<StockResponseDto> stockAry=stockService.findByTicker(ticker);
//        for(StockResponseDto s : stockAry){
//            System.out.println("티커는~"+s.getTicker());
//        }
//    }
}
