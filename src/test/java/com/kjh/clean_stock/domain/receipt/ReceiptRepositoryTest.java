package com.kjh.clean_stock.domain.receipt;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.portfolio.PortfolioRepository;
import com.kjh.clean_stock.domain.receipt.*;
import com.kjh.clean_stock.domain.stock.Stock;
import com.kjh.clean_stock.domain.stock.StockRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiptRepositoryTest {
    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    StockService stockService;

    @After
    public void cleanup(){
        receiptRepository.deleteAll();
    }
    @Test
    public void 임시(){

    }
    @Test
    public void 주식주문_불러오기() throws IOException, InterruptedException {
        String name ="테스트_제목";
        String ticker ="테스트_티커";
        BigDecimal price= new BigDecimal(100.01);
        int stockCnt =10;
        Long stockAvr = 100L;
        portfolioRepository.save(Portfolio.builder()
                .name(name)
                .build());
        //stockService.saveKOSPI();
        stockRepository.save(Stock.builder()
                .name(name)
                .price(price)
                .ticker(ticker)
                .build());

        List<Stock> stockList =stockRepository.findAll();
        Stock stock = stockList.get(0);
        List<Portfolio> portfolioLists = portfolioRepository.findAll();
        Portfolio portfolio = portfolioLists.get(0);

        receiptRepository.save(Receipt.builder()
                .stockCnt(stockCnt)
                .stockAvr(stockAvr)
                .portfolio(portfolio)
                .stock(stock)
                .build());

        List<Receipt> receiptLists = receiptRepository.findAll();

        Receipt receipt = receiptLists.get(0);
        assertThat(receipt.getStockCnt()).isEqualTo(stockCnt);
        assertThat(receipt.getStockAvr()).isEqualTo(stockAvr);
    }
}
