package com.kjh.clean_stock.web;


import com.kjh.clean_stock.config.auth.LoginUser;
import com.kjh.clean_stock.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(@LoginUser SessionUser user, Model model){
        if(user != null){
            model.addAttribute("userEmail", user.getEmail());//없으면 로그인 버튼 노출
            model.addAttribute("userPicture",user.getPicture());
        }//해당 로그인 코드 자체가 반복되는데 이를 줄일수 있는 방법이 있을까?
        //로그인관련 업무만 담당하는 로그인 컨트롤러를 만든다.(가장 생각나는 해결책)
        return "index";
    }

}
