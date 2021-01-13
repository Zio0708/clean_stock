package com.kjh.clean_stock.web;


import com.kjh.clean_stock.service.portfolio.PortfolioService;
import com.kjh.clean_stock.web.dto.PortfolioResponseDto;
import com.kjh.clean_stock.web.dto.PortfolioSaveRequestDto;
import com.kjh.clean_stock.web.dto.PortfolioUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReceiptApiController {
    public final PortfolioService portfolioService;

    @PostMapping("/api/v1/portfolio")
    public Long save (@RequestBody PortfolioSaveRequestDto requestDto){
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
