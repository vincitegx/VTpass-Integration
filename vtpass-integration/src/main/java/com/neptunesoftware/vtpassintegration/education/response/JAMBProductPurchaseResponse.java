package com.neptunesoftware.vtpassintegration.education.response;

import com.neptunesoftware.vtpassintegration.education.domain.Content;
import com.neptunesoftware.vtpassintegration.education.domain.TransactionDate;
import lombok.Data;

@Data
public class JAMBProductPurchaseResponse {
    private String code;
    private Content content;
    private String requestId;
    private String amount;
    private TransactionDate transaction_date;
    private String purchased_code;
}
