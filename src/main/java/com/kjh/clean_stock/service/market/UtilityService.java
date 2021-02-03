package com.kjh.clean_stock.service.market;

import com.kjh.clean_stock.web.dto.Receipt.ReceiptViewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UtilityService {

    public BigDecimal calculateProfitRate(BigDecimal curPrice, BigDecimal havePrice){
        BigDecimal price =curPrice.divide(havePrice,4, RoundingMode.HALF_UP);
        price = price.subtract(new BigDecimal(1));
        price = price.multiply(new BigDecimal(100));
        return price.setScale(2,RoundingMode.DOWN);
    }
    public BigDecimal calculateAllProfitPrice(BigDecimal curPrice,BigDecimal havePrice,int amount){
        BigDecimal price =curPrice.subtract(havePrice);
        price= price.multiply(new BigDecimal(amount));
        return price;
    }
    public BigDecimal calculateAllProfitPrice(List<ReceiptViewResponseDto> receiptViewResponseDtoList){
        BigDecimal price = new BigDecimal(0);
        for(ReceiptViewResponseDto receipt : receiptViewResponseDtoList){
            price.add(receipt.getProfitPrice());
        }
        return price;
    }
    public BigDecimal calAllPrice(List<ReceiptViewResponseDto> receiptViewResponseDtoList) {
        BigDecimal price = new BigDecimal(0);
        for(ReceiptViewResponseDto receipt : receiptViewResponseDtoList){
            price.add(receipt.getStockAvr().multiply(new BigDecimal(receipt.getStockCnt())));
        }
        return price;
    }
}
