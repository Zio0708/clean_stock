package com.kjh.clean_stock.web.dto;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import lombok.Getter;

@Getter
public class ReceiptResponseDto {
    private Long id;
    private int stockCnt;
    private int stockAvr;

    public ReceiptResponseDto(Portfolio entity){
        this.id =entity.getId();
        this.stockCnt = entity.getStockCnt();
        this.stockAvr = entity.getStockAvr();
    }
}
