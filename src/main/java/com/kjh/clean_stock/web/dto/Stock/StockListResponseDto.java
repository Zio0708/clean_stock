package com.kjh.clean_stock.web.dto.Stock;

import com.kjh.clean_stock.domain.stock.Stock;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class StockListResponseDto {
    private Long id;
    private String name;
    private String ticker;
    private BigDecimal price;

    public StockListResponseDto(Stock entity){
        this.id =entity.getId();
        this.name =entity.getName();
        this.ticker =entity.getTicker();
        this.price = entity.getPrice();
    }
}
