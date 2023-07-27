package com.neptunesoftware.vtpassintegration.transaction.controller;

import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/vi/transactions")
@RequiredArgsConstructor
public class TranscationController {
    private final TransactionService transactionService ;
    @PostMapping("requery")
    public ResponseEntity<?> queryTransaction(@RequestParam String requestId){
        return ResponseEntity.ok(transactionService.queryTransaction(requestId));

    }
}
