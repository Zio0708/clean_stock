package com.kjh.clean_stock.web;


import com.kjh.clean_stock.config.auth.LoginUser;
import com.kjh.clean_stock.config.auth.dto.SessionUser;
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
    public String index(Model model , @LoginUser SessionUser user){
        model.addAttribute("portfolio",portfolioService.findAllDesc());
        if(user != null){
            System.out.println("이메일 확인"+ user.getEmail() + " "+user.getName());
            model.addAttribute("userEmail", user.getEmail());//없으면 로그인 버튼 노출
        }
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
