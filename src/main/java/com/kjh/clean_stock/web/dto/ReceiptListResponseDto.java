package com.kjh.clean_stock.web.dto;


import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.receipt.Receipt;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReceiptListResponseDto {
    private Long id;
    private int stockCnt;
    private Long stockAvr;

    public ReceiptListResponseDto(Receipt entity){
        this.id =entity.getId();
        this.stockCnt = entity.getStockCnt();
        this.stockAvr = entity.getStockAvr();
    }
}
