package com.kjh.clean_stock.web.dto.Stock;

import com.kjh.clean_stock.domain.stock.Stock;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor
public class StockSaveRequestDto {
    private String name;
    private String ticker;
    private BigDecimal price;

    @Builder
    public StockSaveRequestDto(String name,String ticker,BigDecimal price){
        this.name= name;
        this.ticker=ticker;
        this.price = price;
    }

    public Stock toEntity(){
        return Stock.builder()
                .name(name)
                .ticker(ticker)
                .price(price)
                .build();
    }
}
