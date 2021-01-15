package com.kjh.clean_stock.web.dto;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.receipt.Receipt;
import lombok.Getter;

@Getter
public class ReceiptResponseDto {
    private Long id;
    private int stockCnt;
    private Long stockAvr;
    private Long portfolio_id;

    public ReceiptResponseDto(Receipt entity){
        this.id =entity.getId();
        this.stockCnt = entity.getStockCnt();
        this.stockAvr = entity.getStockAvr();
        this.portfolio_id = entity.getPortfolio().getId(); //다대일 관계에서 부모키를 가져오는 방법은 뭘까?
        //현재 portfolio자체를 응답으로 주는 방식은 좋아보이지 않아서 id만 가져옴
    }
}
