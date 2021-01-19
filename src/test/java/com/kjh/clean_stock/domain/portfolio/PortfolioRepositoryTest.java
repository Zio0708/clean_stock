package com.kjh.clean_stock.domain.portfolio;

import com.kjh.clean_stock.domain.user.Role;
import com.kjh.clean_stock.domain.user.User;
import com.kjh.clean_stock.domain.user.UserRepository;
import com.kjh.clean_stock.domain.user.UserRepositoryTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioRepositoryTest {
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    UserRepository userRepository;
    UserRepositoryTest userRepositoryTest;

    @After
    public void cleanup(){
        portfolioRepository.deleteAll();
    }

//    @Test
//    public void 포트폴리오_불러오기(){
//        String name ="테스트_제목";
//        portfolioRepository.save(Portfolio.builder()
//                .name(name)
//                .build());
//
//        List<Portfolio> portfolioLists = portfolioRepository.findAll();
//
//        Portfolio portfolio = portfolioLists.get(0);
//        assertThat(portfolio.getName()).isEqualTo(name);
//    }

    @Test
    public void 유저아이디로_포트폴리오_불러오기(){
        String email = "테스트_유저";
        String name = "테스트_이름";
        String picture = "테스트_사진진";
        Role role = Role.USER;
        userRepository.save(User.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .role(role)
                .build());
        List<User> userList = userRepository.findAll();
        User user = userList.get(0);
        assertThat(user.getName()).isEqualTo(name);
        String portfolio_name ="테스트_제목";
        portfolioRepository.save(Portfolio.builder()
                .name(portfolio_name)
                .user(user)
                .build());
        List<Portfolio> portfolioLists = portfolioRepository.findAll();
        Portfolio portfolio = portfolioLists.get(0);
        assertThat(portfolio.getName()).isEqualTo(portfolio_name);
    }
    @Test
    public void 유저아이디로_포트폴리오_찾아오기() {
        String email = "테스트_유저";
        String name = "테스트_이름";
        String picture = "테스트_사진진";
        Role role = Role.USER;
        userRepository.save(User.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .role(role)
                .build());
        List<User> userList = userRepository.findAll();
        User user = userList.get(0);
        assertThat(user.getName()).isEqualTo(name);
        String portfolio_name ="테스트_제목";
        portfolioRepository.save(Portfolio.builder()
                .name(portfolio_name)
                .user(user)
                .build());
        List<Portfolio> portfolioLists = portfolioRepository.findAll();
        Portfolio portfolio = portfolioLists.get(0);
        assertThat(portfolio.getName()).isEqualTo(portfolio_name);
        assertThat(portfolioRepository.findByUser_id(user.getId()).get(0).getName()).isEqualTo(portfolio_name);


    }
}
