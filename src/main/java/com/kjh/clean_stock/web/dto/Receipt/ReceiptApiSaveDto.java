package com.kjh.clean_stock.web.dto.Receipt;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReceiptApiSaveDto {
    private int stockCnt;
    private Long stockAvr;
    private Long portfolio_id;

    @Builder
    public ReceiptApiSaveDto(int stockCnt, Long stockAvr, Long portfolio_id){
        this.stockCnt= stockCnt;
        this.stockAvr=stockAvr;
        this.portfolio_id= portfolio_id;
    }

}
