package com.kjh.clean_stock.web.dto.Receipt;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ReceiptApiSaveDto {
    @NotBlank
    private int stockCnt;
    @Min(value = 0 , message = "0 이상의 값을 넣어주세요.")
    @NotBlank
    private Long stockAvr;
    @NotNull
    private Long portfolio_id;
    @NotNull
    private Long stock_id;

    @Builder
    public ReceiptApiSaveDto(int stockCnt, Long stockAvr, Long portfolio_id,Long stock_id){
        this.stockCnt= stockCnt;
        this.stockAvr=stockAvr;
        this.portfolio_id= portfolio_id;
        this.stock_id=stock_id;
    }

}
