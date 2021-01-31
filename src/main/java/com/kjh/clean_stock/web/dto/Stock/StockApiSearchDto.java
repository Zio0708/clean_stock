package com.kjh.clean_stock.web.dto.Stock;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class StockApiSearchDto {
    @NotNull
    private String name;
    @NotNull
    private String ticker;

    @Builder
    public StockApiSearchDto(String name,String ticker){
        this.name= name;
        this.ticker=ticker;
    }
}
