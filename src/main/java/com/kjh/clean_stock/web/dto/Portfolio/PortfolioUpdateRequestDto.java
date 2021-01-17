package com.kjh.clean_stock.web.dto.Portfolio;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioUpdateRequestDto {
    private String name;

    @Builder
    public PortfolioUpdateRequestDto(String name){
        this.name = name;
    }
    public Portfolio toEntity(){
        return Portfolio.builder()
                .name(name)
                .build();
    }

}
