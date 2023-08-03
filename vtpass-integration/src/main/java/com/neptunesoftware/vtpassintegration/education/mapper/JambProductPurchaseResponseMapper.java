package com.neptunesoftware.vtpassintegration.education.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.request.JAMBProductPurchaseRequest;
import com.neptunesoftware.vtpassintegration.education.response.JAMBProductPurchaseResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JambProductPurchaseResponseMapper {


    private final Credentials credentials;

    public TransactionRequest mapPinVendingRequest(JAMBProductPurchaseRequest request, JAMBProductPurchaseResponse response) {
        return TransactionRequest.builder()
                .code(response.getCode())
                .requestId(response.getRequestId())
                .channelName(credentials.getChannelName())
                .chargeAmount(response.getContent().getTransactions().getCommission().toString())
                .tranSender(response.getContent().getTransactions().getEmail())
                .tranReceiver(response.getContent().getTransactions().getPhone())
                .isReversal("N")
                .narration(response.getContent().getTransactions().getProduct_name())
                .paymentCurr(credentials.getPaymentCurrency())
                .serviceId(request.getServiceID())
                .taxAmount(null)
                .tranStatus(response.getContent().getTransactions().getStatus())
                .tranType(response.getContent().getTransactions().getType())
                .tranAmount(response.getContent().getTransactions().getAmount().toString())
                .tranId(response.getContent().getTransactions().getTransactionId())
                .tranAppl(response.getContent().getTransactions().getPlatform())
                .tranMethod(response.getContent().getTransactions().getMethod())
                .tranPurpose(response.getContent().getTransactions().getProduct_name())
                .tranDate(Date.from(Instant.now()).toString())
                .build();
    }
}
