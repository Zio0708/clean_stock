package com.kjh.clean_stock.web.dto.Portfolio;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import lombok.Getter;

@Getter
public class PortfolioResponseDto {
    private Long id;
    private String name;

    public PortfolioResponseDto(Portfolio entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
