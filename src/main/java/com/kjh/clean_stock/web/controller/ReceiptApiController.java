package com.kjh.clean_stock.web.controller;


import com.kjh.clean_stock.service.receipt.ReceiptService;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptApiSaveDto;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptApiUpdateDto;
import com.kjh.clean_stock.web.dto.Receipt.ReceiptResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReceiptApiController {
    public final ReceiptService receiptService;

    @PostMapping("/api/v1/receipt")
    public Long save (@RequestBody ReceiptApiSaveDto requestDto){
        return receiptService.save(requestDto);
    }
    @PutMapping("/api/v1/receipt/{id}")
    public Long update(@PathVariable Long id , @RequestBody ReceiptApiUpdateDto requestDto){
        return receiptService.update(id,requestDto);
    }
    @GetMapping("/api/v1/receipt/{id}")
    public ReceiptResponseDto findById(@PathVariable Long id){
        return receiptService.findById(id);
    }


}