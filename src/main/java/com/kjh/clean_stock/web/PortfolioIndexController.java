package com.kjh.clean_stock.web;


import com.kjh.clean_stock.config.auth.LoginUser;
import com.kjh.clean_stock.config.auth.dto.SessionUser;
import com.kjh.clean_stock.service.portfolio.PortfolioService;
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
public class PortfolioIndexController {
    private final PortfolioService portfolioService;
    private final ReceiptService receiptService;

    @GetMapping("/")
    public String index(Model model , @LoginUser SessionUser user){
        model.addAttribute("portfolio",portfolioService.findAllDesc());
        if(user != null){
            System.out.println("이메일 확인"+ user.getEmail() + " "+user.getName());
            model.addAttribute("userEmail", user.getEmail());//없으면 로그인 버튼 노출
        }
        return "index";
    }
    @GetMapping("/portfolio/save")
    public String portfolioSave(){
        return "portfolio-save";
    }

    @GetMapping("/portfolio/detail/{id}")
    public String portfolioUpdate(@PathVariable Long id , Model model){

        PortfolioResponseDto portfolioResponseDto=  portfolioService.findById(id);
        model.addAttribute("portfolio",portfolioResponseDto);
        model.addAttribute("receipt",receiptService.findAllDesc());
        return "portfolio-detail";
    }
}
