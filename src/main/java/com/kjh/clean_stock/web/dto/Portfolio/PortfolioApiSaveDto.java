package com.kjh.clean_stock.web.dto.Portfolio;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PortfolioApiSaveDto {
    @NotBlank(message = "포트폴리오 이름을 작성하세요.")
    private String name;
    @NotNull
    private Long user_id;

    @Builder
    public PortfolioApiSaveDto(String name , Long user_id){
        this.name = name;
        this.user_id = user_id;
    }

}
