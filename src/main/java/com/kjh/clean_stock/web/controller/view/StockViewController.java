package com.kjh.clean_stock.web.controller.view;


import com.kjh.clean_stock.service.receipt.ReceiptService;
import com.kjh.clean_stock.web.dto.Stock.StockResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class StockViewController {
    private final ReceiptService receiptService;

    @GetMapping("/stock/search")
    public String stockSearch(){
        return "stock-search";
    }

    @GetMapping("/stock/search/result")
    public String postsUpdate(@PathVariable Long id , Model model){
        StockResponseDto dto;
//        model.addAttribute("portfolio",dto);
        return "receipt-detail";
    }
}
