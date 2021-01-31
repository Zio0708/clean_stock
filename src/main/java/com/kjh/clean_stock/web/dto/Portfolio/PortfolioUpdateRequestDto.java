package com.kjh.clean_stock.web.dto.Portfolio;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PortfolioUpdateRequestDto {
    @NotBlank(message="포트폴리오 이름을 입력하세요")
    private String name;
    private User user;

    @Builder
    public PortfolioUpdateRequestDto(String name,User user){
        this.name = name;
        this.user = user;
    }
    public Portfolio toEntity(){
        return Portfolio.builder()
                .name(name)
                .user(user)
                .build();
    }

}
