package com.kjh.clean_stock.service.market;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Service
public class UtilityService {

    public BigDecimal calculateProfitRate(BigDecimal curPrice, BigDecimal havePrice){
        BigDecimal price =curPrice.divide(havePrice,2, RoundingMode.HALF_UP);
        price = price.subtract(new BigDecimal(1));
        price = price.multiply(new BigDecimal(100));
        return price;
    }
    public BigDecimal calculateAllProfitPrice(BigDecimal curPrice,BigDecimal havePrice,int amount){
        BigDecimal price =curPrice.subtract(havePrice);
        price= price.multiply(new BigDecimal(amount));
        return price;
    }
}
