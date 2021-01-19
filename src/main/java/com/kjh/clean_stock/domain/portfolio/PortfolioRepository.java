package com.kjh.clean_stock.domain.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;
import java.util.List;


public interface PortfolioRepository extends JpaRepository<Portfolio, Object> {
    @Query("SELECT p FROM Portfolio p ORDER BY p.id DESC")
    List<Portfolio> findAllDesc();

    List<Portfolio> findByUser_id(Long id);
}
