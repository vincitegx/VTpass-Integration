package com.neptunesoftware.vtpassintegration.data.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.data.request.DataSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.data.response.DataSubscriptionResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
@Component
@RequiredArgsConstructor
public class DataSubscriptionResponseMapper {
    private final Credentials credentials;
    public TransactionRequest apply(DataSubscriptionRequest dataSubscriptionRequest, DataSubscriptionResponse dataSubscriptionResponse) {
        return TransactionRequest.builder()
                .requestId(dataSubscriptionResponse.requestId())
                .channelName(credentials.getChannelName())
                .chargeAmount(dataSubscriptionResponse.content().transactions().commission().toString())
                .tranSender(dataSubscriptionResponse.content().transactions().unique_element())
                .tranReceiver(dataSubscriptionResponse.content().transactions().phone())
                .isReversal("N")
                .narration(dataSubscriptionResponse.content().transactions().product_name())
                .paymentCurr(credentials.getPaymentCurrency())
                .serviceId(dataSubscriptionRequest.getServiceId())
                .taxAmount(null)
                .tranStatus(dataSubscriptionResponse.content().transactions().status())
                .tranType(dataSubscriptionResponse.content().transactions().type())
                .tranAmount(dataSubscriptionResponse.content().transactions().total_amount().toString())
                .tranId(dataSubscriptionResponse.content().transactions().transactionId())
                .tranAppl(dataSubscriptionResponse.content().transactions().platform())
                .tranMethod(dataSubscriptionResponse.content().transactions().method())
                .tranPurpose(dataSubscriptionResponse.content().transactions().product_name())
                .tranDate(Date.from(Instant.now()).toString())
                .build();
    }
}
