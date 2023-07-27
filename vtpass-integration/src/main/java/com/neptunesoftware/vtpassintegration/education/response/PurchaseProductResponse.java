package com.neptunesoftware.vtpassintegration.education.response;

import com.neptunesoftware.vtpassintegration.education.domain.TransactionDate;
import org.springframework.transaction.TransactionStatus;

import java.util.List;


public record PurchaseProductResponse(
        String code,
        String responseDescription,
        String requestId,
        String amount,
        TransactionDate transactionDate,
        String purchasedCode,
        List<String> tokens
) {

}






