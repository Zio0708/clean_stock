package com.kjh.clean_stock.service.portfolio;



import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.portfolio.PortfolioRepository;
import com.kjh.clean_stock.web.dto.PortfolioListResponseDto;
import com.kjh.clean_stock.web.dto.PortfolioResponseDto;
import com.kjh.clean_stock.web.dto.PortfolioSaveRequestDto;
import com.kjh.clean_stock.web.dto.PortfolioUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    @Transactional
    public Long save(PortfolioSaveRequestDto requestDto){
        System.out.println("뭔지 파악이나 해보자");
        System.out.println(requestDto.toEntity());
        return portfolioRepository.save(requestDto.toEntity()).getId();
    }

    public PortfolioResponseDto findById(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("포트폴리오가 존재하지 않습니다."));
        return new PortfolioResponseDto(portfolio);
    }
    @Transactional
    public Long update(Long id, PortfolioUpdateRequestDto requestDto) {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("포트폴리오가 존재하지 않습니다."));
        portfolio.update(requestDto.getName());
        return id;
    }

    @Transactional(readOnly = true)
    public List<PortfolioListResponseDto> findAllDesc(){
        return portfolioRepository.findAllDesc().stream()
                .map(PortfolioListResponseDto::new)
                .collect(Collectors.toList());
    }
}
