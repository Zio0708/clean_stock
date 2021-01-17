package com.kjh.clean_stock.web.dto.Stock;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockApiSearchDto {
    private String name;
    private String ticker;

    @Builder
    public StockApiSearchDto(String name,String ticker){
        this.name= name;
        this.ticker=ticker;
    }
}
