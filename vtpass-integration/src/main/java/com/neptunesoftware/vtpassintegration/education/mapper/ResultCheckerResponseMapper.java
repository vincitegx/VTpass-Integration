package com.neptunesoftware.vtpassintegration.education.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.request.ProductRegRequest;
import com.neptunesoftware.vtpassintegration.education.request.WAECResultCheckerRequest;
import com.neptunesoftware.vtpassintegration.education.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.education.response.WAECResultCheckerResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ResultCheckerResponseMapper {


    private final Credentials credentials;

    public TransactionRequest mapCheckerRequest(WAECResultCheckerRequest request, WAECResultCheckerResponse response) {
        return TransactionRequest.builder()
                .requestId(response.getRequestId())
                .channelName(credentials.getChannelName())
                .chargeAmount(response.getAmount())
//                .tranSender(response.getUniqueElement())
                .tranReceiver(request.getPhone())
                .isReversal("N")
                .narration(response.getContent().getTransaction().getProduct_name())
                .paymentCurr(credentials.getPaymentCurrency())
                .serviceId(request.getServiceID())
                .taxAmount(null)
                .tranStatus(response.getContent().getTransaction().getStatus())
                .tranType(response.getContent().getTransaction().getType())
                .tranAmount(response.getAmount().toString())
                .tranId(response.getContent().getTransaction().getTransactionId())
                .tranAppl(response.getContent().getTransaction().getPlatform())
                .tranMethod(response.getContent().getTransaction().getMethod())
                .tranPurpose(response.getResponse_description())
                .tranDate(Date.from(Instant.now()).toString())
                .build();
    }
}
