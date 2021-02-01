package com.kjh.clean_stock.service.market;

import com.kjh.clean_stock.domain.stock.Stock;
import com.kjh.clean_stock.domain.stock.StockRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KOSPIserviceTest {
    @Autowired
    KOSPIService kospiService;
    @Autowired
    StockRepository stockRepository;
    @After
    public void cleanup(){
        stockRepository.deleteAll();
    }
    @Test
    public void saveKOSPITest(){
        try {
            kospiService.saveKOSPI();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Stock> stockLists = stockRepository.findAll();

        Stock stock = stockLists.get(0);
        System.out.println(stock.getName()+stock.getTicker()+stock.getPrice());
    }
    @Test
    public void updateKOSPITest(){
        try {
            kospiService.updateKOSPI();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Stock> stockLists = stockRepository.findAll();

        Stock stock = stockLists.get(0);
        System.out.println(stock.getName()+stock.getTicker()+stock.getPrice());
    }
}
