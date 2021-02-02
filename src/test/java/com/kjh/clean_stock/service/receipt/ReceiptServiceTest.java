package com.kjh.clean_stock.service.receipt;

import com.kjh.clean_stock.service.market.UtilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiptServiceTest {
    @Autowired
    UtilityService utilityService;

    @Test
    public void CalculateProfitRateTest(){
        Long curPrice=100L;
        Long havePrice=100L;
        Long profitRate = utilityService.calculateProfitRate(curPrice,havePrice);
        System.out.println(profitRate);
        assertThat(profitRate).isEqualTo(0);
    }
    @Test
    public void CalculateProfitPriceTest(){
        Long curPrice=100L;
        Long havePrice=100L;
        Long profitPrice = utilityService.calculateAllProfitPrice(curPrice,havePrice,1);
        System.out.println(profitPrice);
        assertThat(profitPrice).isEqualTo(0);
    }
    //테스트 이후에 만든다면 이렇게 하는게...맞나..?
}
