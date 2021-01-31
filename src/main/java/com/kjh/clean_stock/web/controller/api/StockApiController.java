package com.kjh.clean_stock.web.controller.api;


import com.kjh.clean_stock.domain.stock.Stock;
import com.kjh.clean_stock.service.stock.StockService;
import com.kjh.clean_stock.web.dto.Stock.StockApiSearchDto;
import com.kjh.clean_stock.web.dto.Stock.StockListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StockApiController {
    public final StockService stockService;

    @PostMapping("/api/v1/stock/search")
    public List<StockListResponseDto> search (@RequestBody @Valid StockApiSearchDto requestDto){
        String name = requestDto.getName();
        String ticker = requestDto.getTicker();

        List<StockListResponseDto> stockary= stockService.findByName(name);
        if(!stockary.isEmpty()){
            System.out.println(stockary.get(0).getName());
            return stockary;
        }
        return stockary;
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
