package com.kjh.clean_stock.service.stock;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.stock.StockRepository;
import com.kjh.clean_stock.web.dto.Portfolio.PortfolioSaveRequestDto;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptApiSaveDto;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptSaveRequestDto;
import com.kjh.clean_stock.web.dto.Stock.StockSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StockRepository stockRepository;

    @Transactional
    public Long save(StockSaveRequestDto requestDto){
        return stockRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public void saveKOSPI() throws IOException {
        for (int i = 0; i<32 ;i++){
            List<String> cnt = crawling(i);
            for(int j=0;j<cnt.size();j++){
                String[] ary=cnt.get(j).split("/");
                //집어넣은 string 정보 추출
                //System.out.println(ary[0]+" "+ary[2].replaceAll(",",""));
                StockSaveRequestDto requestDto =
                        new StockSaveRequestDto(ary[0],ary[1],
                                new BigDecimal(ary[2].replaceAll(",","")));
                //해당 엔티티에 맞게 가공하여 요청 dto만듬
                stockRepository.save(requestDto.toEntity());
                //저장 요청 전송
            }
        }
    }
    public List crawling(int num) throws IOException {

        String url = "https://finance.naver.com/sise/sise_market_sum.nhn?sosok=0&page="+num;
        Document doc = Jsoup.connect(url).get();
        doc.html();
        Elements elem = doc.select("table>tbody");
        int cnt=0;
        List<String> list = new ArrayList<>();
        for(Element e : elem.select("tr")){
            if(cnt>=6 && e.text().length()>=30) {
                String hre=e.select("a").attr("href").split("=")[1];
                //해당 링크 내의 종목코드만 뽑아낸다.
                Elements ename =e.select("td:eq(1)");
                //종목 이름
                Elements eprice =e.select("td:eq(2)");
                //현재가
                list.add(ename.text() + "/"+hre+"/"+ eprice.text());
                //리스트에 그냥 일괄적으로 삽입
            }
            cnt++;
        }
        //System.out.println(elem.text());
        return list;
    }
}
