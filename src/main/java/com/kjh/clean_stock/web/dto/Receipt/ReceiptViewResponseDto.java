package com.kjh.clean_stock.web.dto.Receipt;


import com.kjh.clean_stock.domain.receipt.Receipt;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReceiptViewResponseDto {
    private Long id;
    private int stockCnt;
    private Long stockAvr;
    private Long stockId;
    private BigDecimal stockPrice;
    private String stockName;
    private String stockTicker;
    //DB 조회만으로 받아올 수 없는 값들 : 수익률, 손익 등
    //서비스로 수익률을 받아오는 것보다 도메인 레벨에서 수익률 , 손익을 가져오는것이 낫다는 생각.
    //수익률 : ((stockPrice - stockAvr)-1)*100
    //손익 : (stockprice-stockAvr)*stockCnt;
    //그러면 계산은 자산 정보 리스트를 반환하는 Dto에서 하는 게 맞는지,
    //엔티티인 receipt에서 하는것이 맞는지 질문드립니다.
    private Long profitRate;//수익률
    private Long profitPrice;//손익

    public ReceiptViewResponseDto(Receipt entity ,Long profitRate,Long profitPrice){
        this.id =entity.getId();
        this.stockCnt = entity.getStockCnt();
        this.stockAvr = entity.getStockAvr();
        this.stockId = entity.getStock().getId();
        this.stockPrice = entity.getStock().getPrice();
        this.stockName = entity.getStock().getName();
        this.stockTicker = entity.getStock().getTicker();
        this.profitRate = profitRate;
        this.profitPrice = profitPrice;
    }
}
