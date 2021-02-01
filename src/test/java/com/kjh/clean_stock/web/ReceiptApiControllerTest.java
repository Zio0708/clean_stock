package com.kjh.clean_stock.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.portfolio.PortfolioRepository;
import com.kjh.clean_stock.domain.receipt.Receipt;
import com.kjh.clean_stock.domain.receipt.ReceiptRepository;
import com.kjh.clean_stock.domain.stock.Stock;
import com.kjh.clean_stock.domain.stock.StockRepository;
import com.kjh.clean_stock.domain.user.User;
import com.kjh.clean_stock.domain.user.UserRepository;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptApiSaveDto;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptSaveRequestDto;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReceiptApiControllerTest {

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

//    @Autowired
//    private ReceiptRepository receiptRepository;
    @MockBean(name = "receiptRepository")
    private ReceiptRepository receiptRepository;
    @MockBean(name = "stockRepository")
    private StockRepository stockRepository;
    @MockBean(name = "portfolioRepository")
    private PortfolioRepository portfolioRepository;
    //mockbean으로 테스트 해봄
    @After
    public void tearDown() throws Exception{
        receiptRepository.deleteAll();
    }
    @Test
    public void 임시(){

    }
//    @Test
//    @WithMockUser(roles="USER")
//    public void ReceiptApiSaveTest() throws Exception{
//        ReceiptApiSaveDto requestDto = ReceiptApiSaveDto.builder()
//                .stock_id(1L)
//                .stockCnt(10)
//                .stockAvr(100L)
//                .portfolio_id(1L)
//                .build();
//        Stock stock = Stock.builder().build();
//        Portfolio portfolio = Portfolio.builder().build();
//        Receipt receipt = Receipt.builder().build();
//        ReceiptSaveRequestDto requestSaveDto = new ReceiptSaveRequestDto(
//                requestDto.getStockCnt(), requestDto.getStockAvr(), portfolio,stock);
//        //테스트 순서 - 요청이 들어옴 -> 포트폴리오가 있나 검사 -> 주식이 있나 검사 -> 각각의 주식과 포트폴리오 설계
//        //레포지토리에 저장
//        //이렇기 때문에 포트폴리오/주식 DB저장을 Mock으로 때우고 receipt 객체를 생성해 버리면 에러가 발생한다.
//        //TransientPropertyValueException 에러 발생
//        //그렇다고 receipt 저장도 Mock으로 때워도 되나..? 테스트 하는 목적에 맞나..?
//        //통합테스트시 필수로 모든 기능을 점검해야 하지만, 유닛테스트인 이상 ControllerTest의 본분에 맞게 하는것이
//        //올바르다고 생각한다.
//        given(stockRepository.findById(0L))
//          .willReturn(java.util.Optional.ofNullable(stock));
//        given(portfolioRepository.findById(0L))
//                .willReturn(java.util.Optional.ofNullable(portfolio));
//        given(receiptRepository.save(requestSaveDto.toEntity()))
//                .willReturn(receipt);
//        //쉬울줄 알았는데 아님 -> 포트폴리오가 없다고 뜬다. -> 의존적일 수 밖에 없는 구조인데
//        String url = "http://localhost:"+port+"/api/v1/receipt";
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//        List<Receipt> all =receiptRepository.findAll();
//
//    }
}
