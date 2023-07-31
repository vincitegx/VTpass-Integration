package com.neptunesoftware.vtpassintegration.education.response;

import com.neptunesoftware.vtpassintegration.education.domain.Transaction;
import com.neptunesoftware.vtpassintegration.education.domain.TransactionDate;
import lombok.Data;

@Data
public class TransactionResponse {
    private String code;
    private Transaction transactions;
    private String response_description;
    private String requestId;
    private Double amount;
    private TransactionDate transaction_date;
    private String purchased_code;
}
