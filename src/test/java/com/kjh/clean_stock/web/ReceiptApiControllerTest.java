package com.kjh.clean_stock.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh.clean_stock.domain.receipt.Receipt;
import com.kjh.clean_stock.domain.receipt.ReceiptRepository;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptApiSaveDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Autowired
    private ReceiptRepository receiptRepository;

    @After
    public void tearDown() throws Exception{
        receiptRepository.deleteAll();
    }
    @Test
    public void 임시(){

    }
//    @Test
//    @WithMockUser(roles="USER")
//    public void Receipt_등록() throws Exception{
//        String name ="테스트_제목";
//        Long id = 1L;
//        int stockCnt =10;
//        Long stockAvr = 100L;
//        Long portfolio_id =1L;
//        ReceiptApiSaveDto requestDto = ReceiptApiSaveDto.builder()
//                .stock_id(id)
//                .stockCnt(stockCnt)
//                .stockAvr(stockAvr)
//                .portfolio_id(portfolio_id)
//                .build();
//
//        String url = "http://localhost:"+port+"/api/v1/receipt";
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//        List<Receipt> all =receiptRepository.findAll();
//
//        assertThat(all.get(0).getStockAvr()).isEqualTo(stockAvr);
//    }
}
