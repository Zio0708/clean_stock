package com.kjh.clean_stock.service.receipt;




import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.portfolio.PortfolioRepository;
import com.kjh.clean_stock.domain.receipt.Receipt;
import com.kjh.clean_stock.domain.receipt.ReceiptRepository;
import com.kjh.clean_stock.domain.stock.Stock;
import com.kjh.clean_stock.domain.stock.StockRepository;
import com.kjh.clean_stock.web.dto.Receipt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final PortfolioRepository portfolioRepository;
    private final StockRepository stockRepository;

    @Transactional
    public Long save(ReceiptApiSaveDto requestDto){ //구입한 주식 정보를 저장하는 서비스 메서드입니다.
        Long portfolio_id = requestDto.getPortfolio_id(); //api 로 받아온 데이터에서 포트폴리오 아이디 추출
        Long stock_id = requestDto.getStock_id(); //api로 받아온 데이터에서 자산 아이디 추출

        Portfolio portfolio = portfolioRepository.findById(portfolio_id) // 포트폴리오 아이디로 포트폴리오 객체 만들기
                .orElseThrow(() -> new IllegalArgumentException("포트폴리오가 존재하지 않습니다."));

        Stock stock = stockRepository.findById(stock_id)
                .orElseThrow(() -> new IllegalArgumentException("자산이 존재하지 않습니다."));

        ReceiptSaveRequestDto requestSaveDto = new ReceiptSaveRequestDto(
                requestDto.getStockCnt(), requestDto.getStockAvr(), portfolio,stock); //엔티티 저장을 위해 주식저장 요청 dto 만듬

        return receiptRepository.save(requestSaveDto.toEntity()).getId(); //주식저장 저장 요청을 jpaRepository로 전송~~
        //...서비스메서드에 이렇게 데이터를 가지고 가공하는 과정이 있는데 너무 난잡한 것 같다.
        //내 생각에는 이런 id로 서치하는 과정이 생략되어 다른 부분에 들어가고 서비스에는 추상적인 내용만 있어야 하는 것 같다
        //하지만 엔티티 클래스에서 repository를 통해 서치하는 과정을 넣는 것도 깔끔하지 않은것 같다(또는
        //뭐가 맞는건지 나중에 리팩토링을 통해 알아봐야지
    }

    public ReceiptResponseDto findById(Long id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("구입정보가 존재하지 않습니다."));
        return new ReceiptResponseDto(receipt);
    }
    @Transactional
    public Long update(Long id, ReceiptApiUpdateDto requestDto) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("구입정보가 존재하지 않습니다."));
        Portfolio portfolio = portfolioRepository.findById(receipt.getPortfolio().getId()) // 포트폴리오 아이디로 포트폴리오 객체 만들기
                .orElseThrow(() -> new IllegalArgumentException("포트폴리오가 존재하지 않습니다."));
        receipt.update(requestDto.getStockCnt(),requestDto.getStockAvr(),portfolio);
        return id;
    }

    @Transactional(readOnly = true)
    public List<ReceiptListResponseDto> findAllDesc(){
        return receiptRepository.findAllDesc().stream()
                .map(ReceiptListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<ReceiptListResponseDto> findByPortfolioId(Long id){
        return receiptRepository.findByPortfolio_id(id).stream()
                .map(ReceiptListResponseDto::new)
                .collect(Collectors.toList());
    }
}
