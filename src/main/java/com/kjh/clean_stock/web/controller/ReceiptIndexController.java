package com.kjh.clean_stock.web.controller;


import com.kjh.clean_stock.config.auth.LoginUser;
import com.kjh.clean_stock.config.auth.dto.SessionUser;
import com.kjh.clean_stock.service.receipt.ReceiptService;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptApiUpdateDto;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptListResponseDto;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptResponseDto;
import com.kjh.clean_stock.web.dto.Stock.StockResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReceiptIndexController {
    private final ReceiptService receiptService;

    @GetMapping("/receipt/save/portfolio_id/{id}")
    public String receiptSave(@PathVariable Long id, Model model,@LoginUser SessionUser user){
        model.addAttribute("portfolio_id",id);
        return "receipt-save";
    }
    @GetMapping("/receipt/update/receipt_id/{id}")
    public String receiptUpdate(@PathVariable Long id, Model model,@LoginUser SessionUser user){
        if(user != null) {
            model.addAttribute("userEmail", user.getEmail());//없으면 로그인 버튼 노출
            model.addAttribute("userId", user.getId());
            model.addAttribute("portfolio_id",id);
            ReceiptListResponseDto receiptResponseDto = receiptService.findById(id);
            if(receiptResponseDto !=null){
                model.addAttribute("receipt",receiptResponseDto);
            }
        }

        return "receipt-update";
    }

//    @GetMapping("/portfolio/detail/{id}")
//    public String postsUpdate(@PathVariable Long id , Model model){
//        ReceiptResponseDto dto=  receiptService.findById(id);
//        model.addAttribute("portfolio",dto);
//        return "receipt-detail";
//    }
}
