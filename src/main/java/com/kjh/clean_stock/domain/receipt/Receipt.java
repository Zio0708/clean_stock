package com.kjh.clean_stock.domain.receipt;


import com.kjh.clean_stock.domain.portfolio.BaseTimeEntity;
import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.stock.Stock;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity//테이블과 링크함
public class Receipt extends BaseTimeEntity{

    @Id//테이블의 pk필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECEIPT_ID")
    private Long id;//엔간하면 long 타입 자동증가를 쓰자

    @Column
    private int stockCnt;

    @Column
    private Long stockAvr;

    @ManyToOne
    @JoinColumn(name="PORTFOLIO_ID")
    private Portfolio portfolio;//id만 받는줄 알았는데 포트폴리오를 통째로 가져오는것이 맞는가?

    @ManyToOne
    @JoinColumn(name="STOCK_ID")
    private Stock stock;

    @Builder
    public Receipt(int stockCnt, Long stockAvr,Portfolio portfolio,Stock stock){
        this.stockCnt = stockCnt;
        this.stockAvr = stockAvr;
        this.portfolio = portfolio;
        this.stock = stock;
    }

    public void update(int stockCnt, Long stockAvr,Portfolio portfolio){
        this.stockCnt = stockCnt;
        this.stockAvr = stockAvr;
        this.portfolio = portfolio;
        this.stock = stock;
    }
}
