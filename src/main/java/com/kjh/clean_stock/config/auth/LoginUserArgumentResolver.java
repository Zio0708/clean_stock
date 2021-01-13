package com.kjh.clean_stock.config.auth;


import com.kjh.clean_stock.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter){//파라미터 지원하는지 검사하는 코드 깔끔하게 잘썼다..
        boolean isLoginUserAnootation = parameter.getParameterAnnotation(LoginUser.class)!=null;//LoginUser어노테이션 확인
        boolean isUserClass= SessionUser.class.equals(parameter.getParameterType());//SessionUser.class 인지 확인
        return isLoginUserAnootation && isUserClass;//&&를 써서 if 문없이도 깔끔하게 짜보자
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory)throws Exception{
        return httpSession.getAttribute("user");//뭔가 엄청 복잡한거 같은데...세션에서 객체만 가져옴..매개변수는 인터페이스라 있는듯?
    }


}
