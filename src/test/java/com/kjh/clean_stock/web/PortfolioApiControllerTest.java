package com.kjh.clean_stock.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh.clean_stock.config.auth.dto.SessionUser;
import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.portfolio.PortfolioRepository;
import com.kjh.clean_stock.domain.user.Role;
import com.kjh.clean_stock.domain.user.User;
import com.kjh.clean_stock.domain.user.UserRepository;
import com.kjh.clean_stock.web.dto.Portfolio.PortfolioApiSaveDto;
import com.kjh.clean_stock.web.dto.Portfolio.PortfolioSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.context.WebApplicationContext;

import javax.mail.Session;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PortfolioApiControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setup(){
        mvc= MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;
//    @MockBean(name = "userRepository")
//    private UserRepository userRepository;
    //mockbean으로 테스트 해봄
    @After
    public void tearDown() throws Exception{
        portfolioRepository.deleteAll();
    }
//    @Test
//    public void 임시(){
//
//    }
    @Test
    @WithMockUser(roles="USER")//왜쓰냐
    public void Portfolio_등록() throws Exception{
        String name ="name";
        User user = User.builder()
                .name(name)
                .email("jiho9478@naver.com")
                .picture("3")
                .role(Role.USER)
                .build();
        userRepository.save(user);
        User oneUser = userRepository.findAll().get(0);

        Long id = oneUser.getId();

        PortfolioApiSaveDto requestDto = PortfolioApiSaveDto.builder()
                .name("name")
                .user_id(id)
                .build();

        String url = "http://localhost:"+port+"/api/v1/portfolio";

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Portfolio> all =portfolioRepository.findAll();

        assertThat(all.get(0).getName()).isEqualTo(name);
    }

//    @Test
//    @WithMockUser(roles="USER")
//    public void Portfolio_등록_Mock() throws Exception {
//        User user = User.builder()
//                .name("name")
//                .email("jiho9478@naver.com")
//                .picture("3")
//                .role(Role.USER)
//                .build();
//        PortfolioSaveRequestDto requestSaveDto = new PortfolioSaveRequestDto("name",user);
//        PortfolioApiSaveDto requestDto = PortfolioApiSaveDto.builder()
//                .name("name")
//                .user_id(0L)
//                .build();
//
//        given(userRepository.findById(0L))
//                .willReturn(java.util.Optional.ofNullable(user));
//        given(portfolioRepository.save(requestSaveDto.toEntity()).getId())
//                .willReturn(0L);
//        String url = "http://localhost:"+port+"/api/v1/portfolio";
//
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//        //쓰는 방법 생각: 포폴 저장 api를 쓸때는 userRepository에서 id가 있는지 확인하고 유저를 리턴해서 등록하는데
//        //userRepository.findById(user_id)시 유저의 아이디가 없다는 문제가 생김
//        //이를 해결하기 위해서 mock object를 만들어 암튼 유저가 있다고 속여주면 portfolioRepository에서는 속게 된다.
//        //api 테스트 이므로 porfolioRepository도 속이면 api 테스트, repositroy테스트일시 부분별로 작동 테스트 가능
//        //통합테스트에는 맞지 않는 활용법에 가까운 것 같고, 부분적으로 구현해야 하는 서비스같은 것들에 사용 가능한 듯 하다.
//        //이 경우에는 TransientPropertyValueException 이 발생하였다. portfolio내부에는 유저가 있어야 하는데
//        //mock으로 작성하다 보니 user를 미처 저장하지 않고 지나갔기 때문에 자식 객체가 미쳐 저장되지 않았기 때문이다.
//        //mock의 경우에 적절한 사용을 통해 사용을 하는 것이 좋겠다(확실하게 테스트 범위를 정해야 문제가 없다.)
//    }
}
