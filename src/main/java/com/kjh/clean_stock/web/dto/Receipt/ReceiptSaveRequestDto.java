package com.kjh.clean_stock.web.dto.Receipt;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.receipt.Receipt;
import com.kjh.clean_stock.domain.stock.Stock;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ReceiptSaveRequestDto {
    private int stockCnt;
    private BigDecimal stockAvr;
    private Portfolio portfolio;
    private Stock stock;

    @Builder
    public ReceiptSaveRequestDto(int stockCnt,BigDecimal stockAvr,Portfolio portfolio,Stock stock){
        this.stockCnt= stockCnt;
        this.stockAvr=stockAvr;
        this.portfolio= portfolio;
        this.stock = stock;
    }



    public Receipt toEntity(){
        return Receipt.builder()
                .stockCnt(stockCnt)
                .stockAvr(stockAvr)
                .portfolio(portfolio)
                .stock(stock)
                .build();
    }
}
