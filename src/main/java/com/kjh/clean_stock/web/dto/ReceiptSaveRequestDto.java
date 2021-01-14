package com.kjh.clean_stock.web.dto;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.receipt.Receipt;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReceiptSaveRequestDto {
    private int stockCnt;
    private Long stockAvr;
    private Portfolio portfolio;

    @Builder
    public ReceiptSaveRequestDto(int stockCnt,Long stockAvr,Portfolio portfolio){
        this.stockCnt= stockCnt;
        this.stockAvr=stockAvr;
        this.portfolio= portfolio;
    }



    public Receipt toEntity(){
        return Receipt.builder()
                .stockCnt(stockCnt)
                .stockAvr(stockAvr)
                .portfolio(portfolio)
                .build();
    }
}
