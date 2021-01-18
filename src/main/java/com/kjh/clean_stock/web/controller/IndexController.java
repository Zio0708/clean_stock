package com.kjh.clean_stock.web.controller;


import com.kjh.clean_stock.config.auth.LoginUser;
import com.kjh.clean_stock.config.auth.dto.SessionUser;
import com.kjh.clean_stock.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final StockService stockService;
    @GetMapping("/")
    public String index(@LoginUser SessionUser user, Model model){
        if(user != null){
            model.addAttribute("userEmail", user.getEmail());//없으면 로그인 버튼 노출
            model.addAttribute("userPicture",user.getPicture());
        }//해당 로그인 코드 자체가 반복되는데 이를 줄일수 있는 방법이 있을까?
        // 모든 사이트 주소에 해당 로그인 코드 복붙하는 건 이상한거 같다.
        //로그인관련 업무만 담당하는 로그인 컨트롤러를 만든다.(가장 생각나는 해결책)
        try {
            stockService.saveKOSPI();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "index";
    }

}
