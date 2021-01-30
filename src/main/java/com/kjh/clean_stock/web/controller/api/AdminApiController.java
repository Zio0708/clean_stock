package com.kjh.clean_stock.web.controller.api;


import com.kjh.clean_stock.service.market.KOSPIService;
import com.kjh.clean_stock.service.stock.StockService;
import com.kjh.clean_stock.web.dto.Stock.StockApiSearchDto;
import com.kjh.clean_stock.web.dto.Stock.StockListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AdminApiController {
    KOSPIService kospiService;

    @PostMapping("/admin/search/kospi")
    public void searchKOSPI (){
        try {
            kospiService.saveKOSPI();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//    @PostMapping("/api/v1/stock/search")
//    public String search (@RequestBody StockApiSearchDto requestDto){
//        String name = requestDto.getName();
//        String ticker = requestDto.getTicker();
//
//        List<StockListResponseDto> stockary= stockService.findByName(name);
//        if(!stockary.isEmpty()){
//            System.out.println("주식명은");
//            return (stockary.get(0).getName());
//        }
//        return "없음";
//    }


}
