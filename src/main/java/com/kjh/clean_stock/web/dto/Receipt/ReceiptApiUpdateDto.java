package com.kjh.clean_stock.web.dto.Receipt;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.receipt.Receipt;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReceiptApiUpdateDto {
    private int stockCnt;
    private Long stockAvr;
    private Long portfolio_id;
    private Long stock_id;

    @Builder
    public ReceiptApiUpdateDto(int stockCnt, Long stockAvr, Long portfolio_id,Long stock_id){
        this.stockCnt= stockCnt;
        this.stockAvr=stockAvr;
        this.portfolio_id=portfolio_id;
        this.stock_id=stock_id;
    }
}
