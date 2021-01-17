package com.kjh.clean_stock.domain.stock;

import com.kjh.clean_stock.domain.receipt.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT p FROM Stock p ORDER BY p.id DESC")
    List<Receipt> findAllDesc();

}
