package com.kjh.clean_stock.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT p FROM Stock p ORDER BY p.id DESC")
    List<Stock> findAllDesc();
    List<Stock> findTop5ByNameLike(String name);
    List<Stock> findTop5ByTickerLike(String ticker);//ORDER BY 붙이는 것을 고려해보자.
}
