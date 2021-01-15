package com.kjh.clean_stock.config.auth;


import com.kjh.clean_stock.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//시큐리티 ON
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable() //솔직히 h2 콘솔쓰려고 끈다는데 아직 뭔 명령어인지는 모름
                .and()
                    .authorizeRequests()//URL 별로 권한 관리를 설정하는 명령어란다..andMatchers로 대상을 지정하는 듯하다
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()//나머지들의 권한도 일괄적으로 설정가능하다
                .and()
                    .logout()
                        .logoutSuccessUrl("/")//로그아웃기능도 있어야지 -> 성공시 /주소로 탈출
                .and()
                    .oauth2Login()// oauth2 로그인기능 설정
                        .userInfoEndpoint()//그 안에 또 설정 들어감;
                            .userService(customOAuth2UserService);//으음...로그인 성공시 후속조치를 할 인터페이스를 등록한다(?)
        http
                .formLogin()
                    .defaultSuccessUrl("/");//로그인이 성공했을때 어디로 갈지 표시하여 주는 기능
        //지금은 여기서 만들어준 로그인 화면이 있지만 괜히 사이트 별로 로그인 화면이 있는것이 아니다.
        //로그인 화면을 만들어서 로그인이 필요한 행동을 할때 그 화면으로 가도록 하자.
    }
}
