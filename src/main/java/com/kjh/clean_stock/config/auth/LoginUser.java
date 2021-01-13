package com.kjh.clean_stock.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { //어노테이션...? -> 대부분 메타데이터(데이터를 위한 데이터를 뜻함) 데이터에 대한 설명을 담고 있다.
    //해당 데이터에 대한 정의를 하는 것 같다 타겟으로 생성되는 위치를 parameter로 지정하고 retention으로 컴파일 이후에도 계속 사용하게 한다.
    //어노테이션 사용시에 @interface로 지정하여 어노테이션을 사용해 보자!!!제발!!
}
