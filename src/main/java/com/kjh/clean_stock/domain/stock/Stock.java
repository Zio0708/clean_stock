package com.kjh.clean_stock.domain.stock;


import com.kjh.clean_stock.web.dto.Stock.StockSaveRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity//테이블과 링크함
public class Stock{

    @Id//테이블의 pk필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STOCK_ID")
    private Long id;//엔간하면 long 타입 자동증가를 쓰자

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String ticker;

    @DecimalMin(value = "0.0" ,inclusive=false)
    @Column(precision = 11, scale = 2)
    private BigDecimal price;

    @Builder
    public Stock(String name, String ticker,BigDecimal price){
        this.name = name;
        this.ticker = ticker;
        this.price = price;
    }

    public void update(String name, String ticker,BigDecimal price){
        this.name = name;
        this.ticker = ticker;
        this.price = price;
    }
    public void update(StockSaveRequestDto requestDto){
        this.name = requestDto.getName();
        this.ticker = requestDto.getTicker();
        this.price = requestDto.getPrice();
    }
}
