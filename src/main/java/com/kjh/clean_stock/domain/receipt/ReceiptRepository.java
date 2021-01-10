package com.kjh.clean_stock.domain.receipt;

import com.kjh.clean_stock.domain.receipt.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}
