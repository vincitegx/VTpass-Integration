package com.neptunesoftware.vtpassintegration.data.response;

import lombok.NonNull;

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
//    "response_description": "TRANSACTION SUCCESSFUL",
//    "requestId": "3476we129909djd",
//    "amount": "100.00",
//    "transaction_date": {
//        "date": "2020-02-21 14:13:02.000000",
//        "timezone_type": 3,
//        "timezone": "Africa/Lagos"
//     },
//     "purchased_code": ""