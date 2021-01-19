package com.kjh.clean_stock.web.dto.Portfolio;

import com.kjh.clean_stock.domain.portfolio.Portfolio;
import com.kjh.clean_stock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioUpdateRequestDto {
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
