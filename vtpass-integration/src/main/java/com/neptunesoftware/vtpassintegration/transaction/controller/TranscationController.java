package com.neptunesoftware.vtpassintegration.transaction.controller;

import com.neptunesoftware.vtpassintegration.transaction.request.CallBackRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.CallBackResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class TranscationController {
    private final TransactionService transactionService;
    @PostMapping("query")
    public ResponseEntity<?> queryTransaction(@RequestParam String requestId){
        return ResponseEntity.ok(transactionService.queryTransaction(requestId));
    }

    @PostMapping("call")
    public ResponseEntity<CallBackResponse> callback(@RequestBody CallBackRequest request){
        return ResponseEntity.ok(transactionService.callBack(request));
    }
}
