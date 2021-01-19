package com.kjh.clean_stock.web.controller;


import com.kjh.clean_stock.service.portfolio.PortfolioService;
import com.kjh.clean_stock.web.dto.Portfolio.PortfolioApiSaveDto;
import com.kjh.clean_stock.web.dto.Portfolio.PortfolioResponseDto;
import com.kjh.clean_stock.web.dto.Portfolio.PortfolioSaveRequestDto;
import com.kjh.clean_stock.web.dto.Portfolio.PortfolioUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PortfolioApiController {
    public final PortfolioService portfolioService;

    @PostMapping("/api/v1/portfolio")
    public Long save (@RequestBody PortfolioApiSaveDto requestDto){
        return portfolioService.save(requestDto);
    }
    @PutMapping("/api/v1/portfolio/{id}")
    public Long update(@PathVariable Long id , @RequestBody PortfolioUpdateRequestDto requestDto){
        return portfolioService.update(id,requestDto);
    }
    @GetMapping("/api/v1/portfolio/{id}")
    public PortfolioResponseDto findById(@PathVariable Long id){
        return portfolioService.findById(id);
    }


}
