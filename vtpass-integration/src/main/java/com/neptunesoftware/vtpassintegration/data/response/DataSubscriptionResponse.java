package com.neptunesoftware.vtpassintegration.data.response;

import lombok.NonNull;

public record DataSubscriptionResponse(
        @NonNull
        String code,
        Content content,
        String response_description,
        String requestId,
        String amount,
        TransactionDate transaction_date,
        String purchased_code
) {
}
