package com.kjh.clean_stock.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioSaveRequestDto {
    private String name;

    @Builder
    public PortfolioSaveRequestDto(String name){
        this.name= name;
    }
    public Portfolio toEntitiy(){
        return Portfolio.builder()
                .name(name)
                .build();
    }
}
