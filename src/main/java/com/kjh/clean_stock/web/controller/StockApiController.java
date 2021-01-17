package com.kjh.clean_stock.web.controller;


import com.kjh.clean_stock.service.stock.StockService;
import com.kjh.clean_stock.web.dto.Stock.StockApiSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StockApiController {
    public final StockService stockService;

    @PostMapping("/api/v1/stock/search")
    public List search (@RequestBody StockApiSearchDto requestDto){
        String name = requestDto.getName();
        String ticker = requestDto.getTicker();
        return stockService.findByName(name);
    }


}
