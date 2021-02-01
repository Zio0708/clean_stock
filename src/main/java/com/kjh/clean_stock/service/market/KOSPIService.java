package com.kjh.clean_stock.service.market;

import com.kjh.clean_stock.domain.stock.StockRepository;
import com.kjh.clean_stock.service.stock.StockService;
import com.kjh.clean_stock.web.dto.Stock.StockSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class KOSPIService {
    private final StockRepository stockRepository;
    private final StockService stockService;
    private final CrawlingService crawlingService;

    public List<String> getKospiCrawlData(Elements elem){
        int line=0;
        List<String> list = new ArrayList<>();
        for(Element e : elem.select("tr")){
            if(line>=6 && e.text().length()>=30) { //초반 6줄 제외하고 줄 길이 체크 (따로 유효성 메서드 제작필요)
                String hre=e.select("a").attr("href").split("=")[1];
                //해당 링크 내의 종목코드만 뽑아낸다.
                Elements ename =e.select("td:eq(1)");
                //종목 이름
                Elements eprice =e.select("td:eq(2)");
                //현재가
                list.add(ename.text() + "/"+hre+"/"+ eprice.text());
                //리스트에 그냥 일괄적으로 삽입
            }
            line++;
        }
        return list;
    }
    @Transactional
    public void saveKOSPI() throws IOException, InterruptedException {
        int kospiPage=2;
        String kospiUrl = "https://finance.naver.com/sise/sise_market_sum.nhn?sosok=0&page=";
        for (int page = 0; page<= kospiPage ; page++){

            Document doc = crawlingService.crawlToDocument(kospiUrl + page);
            Elements elements = crawlingService.selectTbodyElements(doc);
            List<String> kospiAry = getKospiCrawlData(elements);

            for(int j=0; j<kospiAry.size(); j++){
                String[] ary=kospiAry.get(j).split("/"); //집어넣은 string 정보 추출
                StockSaveRequestDto requestDto =
                        new StockSaveRequestDto(ary[0],ary[1],
                                new BigDecimal(ary[2].replaceAll(",","")));
                //해당 엔티티에 맞게 가공하여 요청 dto만듬
                //stockRepository.save(requestDto.toEntity());
                stockService.save(requestDto);
                //저장 요청 전송이지만...
                //서비스에서 다른 서비스를 요청하는 건? 코스피 서비스가 주식 서비스에 역할을 넘겨야 하는거 아닌가?
                //repository를 불러야 하는지 service를 부르는지 확인 요망.
            }
            TimeUnit.SECONDS.sleep(5);
        }
    }
    @Transactional
    public void updateKOSPI() throws IOException, InterruptedException {
        int kospiPage=2;
        String kospiUrl = "https://finance.naver.com/sise/sise_market_sum.nhn?sosok=0&page=";
        for (int page = 0; page<= kospiPage ; page++){
            Document doc = crawlingService.crawlToDocument(kospiUrl + page);
            Elements elements = crawlingService.selectTbodyElements(doc);
            List<String> kospiAry = getKospiCrawlData(elements);

            for(int j=0; j<kospiAry.size(); j++){
                String[] ary=kospiAry.get(j).split("/"); //집어넣은 string 정보 추출
                StockSaveRequestDto requestDto =
                        new StockSaveRequestDto(ary[0],ary[1],
                                new BigDecimal(ary[2].replaceAll(",","")));
                //해당 엔티티에 맞게 가공하여 요청 dto만듬
                stockService.updateDaliy(requestDto);
                //업데이트 요청 전송
                //중요한 사실 1.기존에 존재하는 경우 일반적으로 업데이트
                //2.존재하지 않는 경우 새롭게 업로드
            }
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
