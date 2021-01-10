package com.kjh.clean_stock.domain.portfolio;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //BaseTimeEntity 상속시 필드들도 칼럼에 편입하는 라이브러리(별게 다 있다)
@EntityListeners(AuditingEntityListener.class)//Auditing 기능 ON
public class BaseTimeEntity {

    @CreatedDate//이게 시간을 자동 저장시켜주는 기능입니다.(전에 프로젝트에서 미리 쓸걸)
    private LocalDateTime createdDate;

    @LastModifiedDate//수정할때도 저장이 된다!
    private LocalDateTime modifiedDate;
}
