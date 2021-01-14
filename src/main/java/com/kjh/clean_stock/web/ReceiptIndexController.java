package com.kjh.clean_stock.web;


import com.kjh.clean_stock.service.receipt.ReceiptService;
import com.kjh.clean_stock.web.dto.PortfolioResponseDto;
import com.kjh.clean_stock.web.dto.ReceiptResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ReceiptIndexController {
    private final ReceiptService receiptService;

    @GetMapping("/receipt/save/portfolio_id/{id}")
    public String receiptSave(@PathVariable Long id, Model model){
        model.addAttribute("portfolio_id",id);
        return "receipt-save";
    }

//    @GetMapping("/portfolio/detail/{id}")
//    public String postsUpdate(@PathVariable Long id , Model model){
//        ReceiptResponseDto dto=  receiptService.findById(id);
//        model.addAttribute("portfolio",dto);
//        return "receipt-detail";
//    }
}
