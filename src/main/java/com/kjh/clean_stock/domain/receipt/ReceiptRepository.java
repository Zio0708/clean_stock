package com.kjh.clean_stock.domain.receipt;

import com.kjh.clean_stock.domain.receipt.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    @Query("SELECT p FROM Receipt p ORDER BY p.id DESC")
    List<Receipt> findAllDesc();
    List<Receipt> findByPortfolio_id(Long id);
}
