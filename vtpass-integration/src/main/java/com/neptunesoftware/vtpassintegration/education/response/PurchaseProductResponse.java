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






//public class PurchaseProductResponse {
//
//    private String code;
//    private String responseDescription;
//    private String requestId;
//    private String amount;
//    private TransactionDate transactionDate;
//    private String purchasedCode;
//    private List<String> tokens;
//
//
//   // private String[] tokens;
//}