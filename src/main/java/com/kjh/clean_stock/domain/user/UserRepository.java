package com.kjh.clean_stock.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;//이게 뭐지 -> null 처리가 매우 고통스럽기 때문에 null 일수도 아닐수도 있는 객체 wrapper 클래스를 만듬
//진짜 개발자들은 귀찮은 일을 최소화(생산성 최대화)에 미쳤다 -> 다른 코드도 생산성 있는 코드를 찾아보자

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}