package com.neptunesoftware.vtpassintegration.data.response;



public record DataSubscriptionResponse(
        String code,
        Content content,
        String response_description,
        String requestId,
        String amount,
        TransactionDate transaction_date,
        String purchased_code
) {
}