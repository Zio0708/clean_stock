package com.kjh.clean_stock.web.dto.Portfolio;


import com.kjh.clean_stock.domain.portfolio.Portfolio;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PortfolioListResponseDto {
    private Long id;
    private String name;
    private LocalDateTime modifiedDate;

    public PortfolioListResponseDto(Portfolio entity){
        this.id =entity.getId();
        this.name =entity.getName();
        this.modifiedDate =entity.getModifiedDate();
    }
}
