package com.kjh.clean_stock.service.market;

import com.kjh.clean_stock.domain.stock.StockRepository;
import com.kjh.clean_stock.web.dto.Stock.StockSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class KOSPIService {
    private final StockRepository stockRepository;
    private CrawlingService crawlingService;

    @Transactional
    public void saveKOSPI() throws IOException, InterruptedException {
        int kospiPage=32;
        String kospiUrl = "https://finance.naver.com/sise/sise_market_sum.nhn?sosok=0&page=";
        for (int page = 0; page<= kospiPage ; page++){
            List<String> cnt = crawlingService.kospiCrawling(kospiUrl + page);
            for(int j=0; j<cnt.size(); j++){
                String[] ary=cnt.get(j).split("/");
                //집어넣은 string 정보 추출
                StockSaveRequestDto requestDto =
                        new StockSaveRequestDto(ary[0],ary[1],
                                new BigDecimal(ary[2].replaceAll(",","")));
                //해당 엔티티에 맞게 가공하여 요청 dto만듬
                stockRepository.save(requestDto.toEntity());
                //저장 요청 전송
            }
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
