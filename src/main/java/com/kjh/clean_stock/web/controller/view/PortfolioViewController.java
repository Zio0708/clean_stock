package com.kjh.clean_stock.web.controller.view;


import com.kjh.clean_stock.config.auth.LoginUser;
import com.kjh.clean_stock.config.auth.dto.SessionUser;
import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.receipt.Receipt;
import com.kjh.clean_stock.domain.user.User;
import com.kjh.clean_stock.service.market.UtilityService;
import com.kjh.clean_stock.service.portfolio.PortfolioService;
import com.kjh.clean_stock.service.receipt.ReceiptService;
import com.kjh.clean_stock.service.stock.StockService;
import com.kjh.clean_stock.web.dto.Portfolio.PortfolioListResponseDto;
import com.kjh.clean_stock.web.dto.Portfolio.PortfolioResponseDto;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptListResponseDto;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptViewResponseDto;
import com.kjh.clean_stock.web.dto.Stock.StockListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PortfolioViewController {
    private final PortfolioService portfolioService;
    private final ReceiptService receiptService;
    private final UtilityService utilityService;

    @GetMapping("/portfolio")
    public String index(Model model , @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userEmail", user.getEmail());//없으면 로그인 버튼 노출
            model.addAttribute("userId", user.getId());
            List<PortfolioListResponseDto> portfolioAry = portfolioService.findByUserId(user.getId());
            if (!portfolioAry.isEmpty()) {
                model.addAttribute("portfolio", portfolioAry);
//                List<ReceiptListResponseDto> receiptList = receiptService.findByPortfolioId(portfolioAry.get(0).getId());
//                if (!receiptList.isEmpty()) {
//                    model.addAttribute("receipt", receiptList);
//                }
            }
        }
        return "portfolio";
    }
    @GetMapping("/portfolio/save")
    public String portfolioSave(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("userEmail", user.getEmail());//없으면 로그인 버튼 노출
            model.addAttribute("userId", user.getId());
        }
        return "portfolio-save";
    }

    @GetMapping("/portfolio/detail/{id}")
    public String portfolioUpdate(@PathVariable Long id , Model model,@LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("userEmail", user.getEmail());//없으면 로그인 버튼 노출
            model.addAttribute("userId", user.getId());
            PortfolioResponseDto portfolioResponseDto=  portfolioService.findById(id);
            if(portfolioResponseDto != null) {
                model.addAttribute("portfolio", portfolioResponseDto);
                //List<ReceiptListResponseDto> receiptList = receiptService.findByPortfolioId(portfolioResponseDto.getId());
                List<ReceiptViewResponseDto> receiptViewResponseDtoList
                        = receiptService.findViewByPortfolioId(portfolioResponseDto.getId());

                //가져온 자산현황들에 수익률과 수익을 추가해야함.
                if(receiptViewResponseDtoList !=null){
                    BigDecimal portfolioHavePrice =
                            utilityService.calAllPrice(receiptViewResponseDtoList);
                    BigDecimal portfolioAllProfitPrice =
                            utilityService.calculateAllProfitPrice(receiptViewResponseDtoList);
                    BigDecimal portfolioCurPrice =
                            portfolioHavePrice.add(portfolioAllProfitPrice);
                    BigDecimal portfolioPriceRate =
                            utilityService.calculateProfitRate(portfolioCurPrice,portfolioHavePrice);
                    model.addAttribute("receipt",receiptViewResponseDtoList);
                    model.addAttribute("portfolioHavePrice",portfolioHavePrice);
                    model.addAttribute("portfolioAllProfitPrice",portfolioAllProfitPrice);
                    model.addAttribute("portfolioCurPrice",portfolioCurPrice);
                    model.addAttribute("portfolioPriceRate",portfolioPriceRate);


                }
            }
        }

        return "portfolio-detail";
    }
}
