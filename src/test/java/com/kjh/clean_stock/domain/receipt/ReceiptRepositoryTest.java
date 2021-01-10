package com.kjh.clean_stock.domain.receipt;

import com.kjh.clean_stock.domain.receipt.*;
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
public class ReceiptRepositoryTest {
    @Autowired
    ReceiptRepository receiptRepository;

    @After
    public void cleanup(){
        receiptRepository.deleteAll();
    }

    @Test
    public void 주식주문_불러오기(){
        int stockCnt =10;
        Long stockAvr = 100L;
        receiptRepository.save(Receipt.builder()
                .stockCnt(stockCnt)
                .stockAvr(stockAvr)
                .build());

        List<Receipt> receiptLists = receiptRepository.findAll();

        Receipt receipt = receiptLists.get(0);
        assertThat(receipt.getStockCnt()).isEqualTo(stockCnt);
        assertThat(receipt.getStockAvr()).isEqualTo(stockAvr);
    }
}
