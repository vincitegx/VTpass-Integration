package com.neptunesoftware.vtpassintegration.tv.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.tv.response.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TvSubscriptionMapperResponse {
    private final Credentials credentials;
    public TransactionRequest  mapper( TvSubscriptionRequest tvRequest, SubscriptionResponse tvResponse) {
        return TransactionRequest.builder()
                .code(tvResponse.getCode())
                .requestId(tvResponse.getRequestId())
                .serviceId(tvRequest.getServiceID())
                .tranId(tvResponse.getContent().getTransactions().getTransactionId())
                .tranAppl(tvResponse.getContent().getTransactions().getMethod())
                .tranSender(tvResponse.getContent().getTransactions().getUniqueElement())
                .chargeAmount(String.valueOf(tvResponse.getContent().getTransactions().getCommission()))
                .tranReceiver(tvResponse.getContent().getTransactions().getPhone())
                .tranStatus(tvResponse.getContent().getTransactions().getStatus())
                .tranMethod(tvResponse.getContent().getTransactions().getMethod())
                .narration(tvResponse.getContent().getTransactions().getProductName())
                .tranPurpose("Tv subscription")
                .channelName(credentials.getChannelName())
                .isReversal("N")
                .tranDate(Date.from(Instant.now()).toString())
                .tranType(tvResponse.getContent().getTransactions().getType())
                .paymentCurr(credentials.getPaymentCurrency())
                .chargeAmount(String.valueOf(tvResponse.getContent().getTransactions().getTotalAmount()))
                .taxAmount(null)
                .tranAmount(String.valueOf(tvResponse.getContent().getTransactions().getAmount()))
                .build();
    }

    public TransactionResponse mapTvToSubscriptionResponse(SubscriptionResponse responseApi) {
        return new TransactionResponse(responseApi.getCode(),responseApi.getContent().getTransactions().getStatus(),responseApi.getRequestId());
    }

}
