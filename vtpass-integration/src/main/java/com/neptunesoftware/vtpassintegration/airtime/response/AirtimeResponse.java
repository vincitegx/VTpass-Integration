package com.neptunesoftware.vtpassintegration.airtime.response;

import lombok.Data;
import lombok.NonNull;
@Data
public class AirtimeResponse {
    private String code ;
    private String response_description ;
    private String requestId;
    private String transactionId ;
    private String amount;
    private TransactionDate transaction_date;
    private String purchased_code;
}
