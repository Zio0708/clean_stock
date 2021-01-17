package com.kjh.clean_stock.service.receipt;




import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.portfolio.PortfolioRepository;
import com.kjh.clean_stock.domain.receipt.Receipt;
import com.kjh.clean_stock.domain.receipt.ReceiptRepository;
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
    @Transactional
    public Long save(ReceiptApiSaveDto requestDto){ //구입한 주식 정보를 저장하는 서비스 메서드입니다.
        Long id = requestDto.getPortfolio_id(); //api 로 받아온 데이터에서 포트폴리오 아이디 추출

        Portfolio portfolio = portfolioRepository.findById(id) // 포트폴리오 아이디로 포트폴리오 객체 만들기
                .orElseThrow(() -> new IllegalArgumentException("포트폴리오가 존재하지 않습니다."));

        ReceiptSaveRequestDto requestSaveDto = new ReceiptSaveRequestDto(
                requestDto.getStockCnt(), requestDto.getStockAvr(), portfolio); //엔티티 저장을 위해 주식저장 요청 dto 만듬

        return receiptRepository.save(requestSaveDto.toEntity()).getId(); //주식저장 저장 요청을 jpaRepository로 전송~~

    }

    public ReceiptResponseDto findById(Long id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("포트폴리오가 존재하지 않습니다."));
        return new ReceiptResponseDto(receipt);
    }
    @Transactional
    public Long update(Long id, ReceiptApiUpdateDto requestDto) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주식정보가 존재하지 않습니다."));
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
}
