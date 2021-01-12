package com.kjh.clean_stock.web;


import com.kjh.clean_stock.service.portfolio.PortfolioService;
import com.kjh.clean_stock.web.dto.PortfolioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PortfolioService portfolioService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",portfolioService.findAllDesc());
        return "index";
    }
    @GetMapping("/portfolio/save")
    public String postsSave(){
        return "portfolio-save";
    }

    @GetMapping("/portfolio/update/{id}")
    public String postsUpdate(@PathVariable Long id , Model model){
        PortfolioResponseDto dto=  portfolioService.findById(id);
        model.addAttribute("portfolio",dto);
        return "portfolio-update";
    }
}
