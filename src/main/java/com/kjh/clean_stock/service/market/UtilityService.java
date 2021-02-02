package com.kjh.clean_stock.service.market;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UtilityService {

    public Long calculateProfitRate(Long curPrice,Long havePrice){
        return ((curPrice/havePrice)-1)*100;
    }
    public Long calculateAllProfitPrice(Long curPrice,Long havePrice,int amount){
        return (curPrice-havePrice)*amount;
    }
}
