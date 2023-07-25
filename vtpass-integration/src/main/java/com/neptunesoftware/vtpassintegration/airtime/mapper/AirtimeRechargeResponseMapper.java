package com.neptunesoftware.vtpassintegration.airtime.mapper;
import com.neptunesoftware.vtpassintegration.airtime.response.AirtimeResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;

import java.util.function.Function;

public class AirtimeRechargeResponseMapper implements Function<AirtimeResponse, TransactionRequest> {
    @Override
    public TransactionRequest apply(AirtimeResponse airtimeResponse) {
        TransactionRequest transactionRequest = new TransactionRequest(
                airtimeResponse.getCode(),
                airtimeResponse.getResponse_description(),
                airtimeResponse.getRequestId(),
                airtimeResponse.getTransactionId(),
                airtimeResponse.getAmount()
                //airtimeResponse.getTransaction_date()
        );
                return transactionRequest;
    }
}
