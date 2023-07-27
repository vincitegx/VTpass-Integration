package com.neptunesoftware.vtpassintegration.education.response;

import com.neptunesoftware.vtpassintegration.education.domain.Card;
import com.neptunesoftware.vtpassintegration.education.domain.Content;
import com.neptunesoftware.vtpassintegration.education.domain.TransactionDate;
import lombok.Data;

import java.util.List;

@Data
public class JAMBPINVendingResponse {
    private String code;
    private Content content;
    private String response_description;
    private String requestId;
    private String amount;
    private TransactionDate transaction_date;
    private String purchased_code;
    private List<Card> cards;
}

