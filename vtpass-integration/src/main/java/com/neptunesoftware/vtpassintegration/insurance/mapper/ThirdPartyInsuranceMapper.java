package com.neptunesoftware.vtpassintegration.insurance.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.request.ProductRegRequest;
import com.neptunesoftware.vtpassintegration.education.response.ProductRegResponse;
import com.neptunesoftware.vtpassintegration.insurance.request.ThirdPartyInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.ThirdPartyInsuranceResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ThirdPartyInsuranceMapper {

    private final Credentials credentials;

    public TransactionRequest mapRequest(ThirdPartyInsuranceRequest request, ThirdPartyInsuranceResponse response) {

        return TransactionRequest.builder()
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