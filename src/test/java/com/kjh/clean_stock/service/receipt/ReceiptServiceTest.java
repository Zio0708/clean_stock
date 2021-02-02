package com.kjh.clean_stock.service.receipt;

import com.kjh.clean_stock.service.market.UtilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiptServiceTest {
    @Autowired
    UtilityService utilityService;

    @Test
    public void CalculateProfitRateTest(){
        BigDecimal curPrice=new BigDecimal(125500.00);
        BigDecimal havePrice=new BigDecimal(280000.00);
        BigDecimal price =curPrice.divide(havePrice,4, RoundingMode.HALF_UP);
        System.out.println(price);
        price = price.subtract(new BigDecimal(1));
        System.out.println(price);
        price = price.multiply(new BigDecimal(100));
        System.out.println(price);
        BigDecimal profitRate = utilityService.calculateProfitRate(curPrice,havePrice);
        System.out.println(profitRate);
    }
    @Test
    public void CalculateProfitPriceTest(){
        BigDecimal curPrice=new BigDecimal(100);
        BigDecimal havePrice=new BigDecimal(150);
        BigDecimal profitPrice = utilityService.calculateAllProfitPrice(curPrice,havePrice,1);
        System.out.println(profitPrice);
    }
    //테스트 이후에 만든다면 이렇게 하는게...맞나..?
}
