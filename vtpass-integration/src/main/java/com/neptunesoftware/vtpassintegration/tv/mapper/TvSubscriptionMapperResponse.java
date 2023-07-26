package com.neptunesoftware.vtpassintegration.tv.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.tv.response.TvSubscriptionStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TvSubscriptionMapperResponse {
    private final Credentials credentials;
    public TransactionRequest  mapper( TvSubscriptionRequest tvRequest, TvSubscriptionStatusResponse tvResponse) {
        return TransactionRequest.builder()
                .requestId(tvResponse.getRequestId())
                .serviceId(tvRequest.getServiceID())
                .tranId(tvResponse.getContent().getTransactions().getTransactionId())
                .tranAppl(tvResponse.getContent().getTransactions().getMethod())
                .tranSender(tvResponse.getContent().getTransactions().getUniqueElement())
                .chargeAmount(String.valueOf(tvResponse.getContent().getTransactions().getCommission()))
                .tranReceiver(tvResponse.getContent().getTransactions().getPhone())
                .tranStatus(tvResponse.getAmount())
                .tranMethod(tvResponse.getContent().getTransactions().getMethod())
                .narration(tvResponse.getContent().getTransactions().getProductName())
                .tranPurpose("Tv subscription")
                .channelName(credentials.getChannelName())
                .isReversal("N")
                .tranDate(tvResponse.getTransactionDate().getDate())
                .tranType(tvResponse.getContent().getTransactions().getType())
                .paymentCurr(credentials.getPaymentCurrency())
                .chargeAmount(String.valueOf(tvResponse.getContent().getTransactions().getTotalAmount()))
                .taxAmount(null)
                .tranAmount(String.valueOf(tvResponse.getContent().getTransactions().getAmount()))

                .build();

    }

}