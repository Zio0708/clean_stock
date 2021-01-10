package com.kjh.clean_stock.domain.portfolio;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity//테이블과 링크함
public class Portfolio extends BaseTimeEntity{

    @Id//테이블의 pk필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//엔간하면 long 타입 자동증가를 쓰자

    @Column(length = 100, nullable = false)
    private String name;


    //왜쓰냐? - 생성자인 경우 실수해도 확인하기 어려움, 빌더 패턴으로 바로바로 알자!
    @Builder
    public Portfolio(String name){
        this.name = name;

    }
    public void update(String name){
        this.name= name;
    }
}
