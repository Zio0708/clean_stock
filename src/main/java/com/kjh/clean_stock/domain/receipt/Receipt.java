package com.kjh.clean_stock.domain.receipt;


import com.kjh.clean_stock.domain.portfolio.Portfolio;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity//테이블과 링크함
public class Receipt{

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
    private Portfolio portfolio;

    @Builder
    public Receipt(int stockCnt, Long stockAvr){
        this.stockCnt = stockCnt;
        this.stockAvr = stockAvr;
    }

    public void update(int stockCnt, Long stockAvr){
        this.stockCnt = stockCnt;
        this.stockAvr = stockAvr;
    }
}
