package com.kjh.clean_stock.web;


import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.service.portfolio.PortfolioService;
import com.kjh.clean_stock.web.dto.PortfolioResponseDto;
import com.kjh.clean_stock.web.dto.PortfolioSaveRequestDto;
import com.kjh.clean_stock.web.dto.PortfolioUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PortfolioApiController {
    public final PortfolioService portfolioService;

    @PostMapping("/portfolio")
    public Long save (@RequestBody PortfolioSaveRequestDto requestDto){
        return portfolioService.save(requestDto);
    }
    @PutMapping("/portfolio/{id}")
    public Long update(@PathVariable Long id , @RequestBody PortfolioUpdateRequestDto requestDto){
        return portfolioService.update(id,requestDto);
    }
    @GetMapping("/portfolio/{id}")
    public PortfolioResponseDto findById(@PathVariable Long id){
        return portfolioService.findById(id);
    }


}
