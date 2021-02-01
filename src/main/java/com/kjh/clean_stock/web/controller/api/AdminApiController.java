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

    @PostMapping("/admin/save/kospi")
    public void saveKOSPI (){
        try {
            kospiService.saveKOSPI();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/admin/update/kospi")
    public void updateKOSPI (){
        try {
            kospiService.updateKOSPI();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
