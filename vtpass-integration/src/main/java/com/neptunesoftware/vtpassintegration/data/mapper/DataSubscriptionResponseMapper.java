package com.neptunesoftware.vtpassintegration.data.mapper;

import com.neptunesoftware.vtpassintegration.data.response.DataSubscriptionResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class DataSubscriptionResponseMapper implements Function<DataSubscriptionResponse, TransactionRequest> {
    @Override
    public TransactionRequest apply(DataSubscriptionResponse dataSubscriptionResponse) {
        TransactionRequest transactionRequest = new TransactionRequest(
                dataSubscriptionResponse.requestId(),
                dataSubscriptionResponse.purchased_code(),
                dataSubscriptionResponse.response_description(),
                dataSubscriptionResponse.code(),
                dataSubscriptionResponse.amount()
        );
        return transactionRequest;
    }
}
