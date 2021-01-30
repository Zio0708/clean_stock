package com.kjh.clean_stock.web.controller.view;


import com.kjh.clean_stock.config.auth.LoginUser;
import com.kjh.clean_stock.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminViewController {

    @GetMapping("/admin")
    public String admin(@LoginUser SessionUser user,Model model){
        if (user != null) {
            model.addAttribute("userEmail", user.getEmail());//없으면 로그인 버튼 노출
        }
        return "admin";
    }

}
