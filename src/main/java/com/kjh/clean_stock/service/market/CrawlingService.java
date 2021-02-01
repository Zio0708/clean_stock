package com.kjh.clean_stock.service.market;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CrawlingService {

//    public List kospiCrawling(String url) throws IOException {
//        Document doc = Jsoup.connect(url).get();
//        doc.html();
//        Elements elem = doc.select("table>tbody");
//        int line=0;
//        List<String> list = new ArrayList<>();
//        for(Element e : elem.select("tr")){
//            if(line>=6 && e.text().length()>=30) { //초반 6줄 제외하고 줄 길이 체크 (따로 유효성 메서드 제작필요)
//                String hre=e.select("a").attr("href").split("=")[1];
//                //해당 링크 내의 종목코드만 뽑아낸다.
//                Elements ename =e.select("td:eq(1)");
//                //종목 이름
//                Elements eprice =e.select("td:eq(2)");
//                //현재가
//                list.add(ename.text() + "/"+hre+"/"+ eprice.text());
//                //리스트에 그냥 일괄적으로 삽입
//            }
//            line++;
//        }
//        return list;
//    }
    public Document crawlToDocument(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }
    public Elements selectTbodyElements(Document doc) {
        Elements elem = doc.select("table>tbody");
        return elem;
    }
    public List getKospiCrawlData(Elements elem){
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
}
