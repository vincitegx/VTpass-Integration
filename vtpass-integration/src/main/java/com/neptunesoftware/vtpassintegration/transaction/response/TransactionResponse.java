package com.neptunesoftware.vtpassintegration.transaction.response;

import com.neptunesoftware.vtpassintegration.data.response.Content;
import com.neptunesoftware.vtpassintegration.data.response.TransactionDate;

public record TransactionResponse(
        String code,
        Content content,
        String response_description,
        String requestId,
        String amount,
        TransactionDate transaction_date,
        String purchased_code
) {

}
