package com.kjh.clean_stock.web.dto.Receipt;


import com.kjh.clean_stock.domain.receipt.Receipt;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ReceiptListResponseDto {
    private Long id;
    private int stockCnt;
    private Long stockAvr;
    private Long stockId;
    private BigDecimal stockPrice;
    private String stockName;
    private String stockTicker;

    @Builder
    public ReceiptListResponseDto(Receipt entity){
        this.id =entity.getId();
        this.stockCnt = entity.getStockCnt();
        this.stockAvr = entity.getStockAvr();
        this.stockId = entity.getStock().getId();
        this.stockPrice = entity.getStock().getPrice();
        this.stockName = entity.getStock().getName();
        this.stockTicker = entity.getStock().getTicker();
    }
//    public ReceiptListResponseDto(Receipt entity){
//        this.id =entity.getId();
//        this.stockCnt = entity.getStockCnt();
//        this.stockAvr = entity.getStockAvr();
//    }
}
