package com.kjh.clean_stock.service.portfolio;



import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.portfolio.PortfolioRepository;
import com.kjh.clean_stock.domain.user.User;
import com.kjh.clean_stock.domain.user.UserRepository;
import com.kjh.clean_stock.web.dto.Portfolio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(PortfolioApiSaveDto requestDto){
        Long user_id =requestDto.getUser_id();
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        PortfolioSaveRequestDto requestSaveDto = new PortfolioSaveRequestDto(requestDto.getName(),user);
        return portfolioRepository.save(requestSaveDto.toEntity()).getId();
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
    @Transactional(readOnly = true)
    public List<PortfolioListResponseDto> findByUserId(Long id) {
        return portfolioRepository.findByUser_id(id).stream()
                .map(PortfolioListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void delete(Long id){
        Portfolio portfolio =portfolioRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 포트폴리오 없음. id="+id));
        portfolioRepository.delete(portfolio);
    }
}
