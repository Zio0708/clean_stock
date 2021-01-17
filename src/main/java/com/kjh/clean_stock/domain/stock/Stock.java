package com.kjh.clean_stock.domain.stock;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity//테이블과 링크함
public class Stock{

    @Id//테이블의 pk필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STOCK_ID")
    private Long id;//엔간하면 long 타입 자동증가를 쓰자

    @Column
    private String name;

    @Column
    private String ticker;

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
}
