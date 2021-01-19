package com.kjh.clean_stock.web.dto.Portfolio;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioApiSaveDto {
    private String name;
    private Long user_id;

    @Builder
    public PortfolioApiSaveDto(String name , Long user_id){
        this.name = name;
        this.user_id = user_id;
    }

}
