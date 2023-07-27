package com.neptunesoftware.vtpassintegration.education.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.request.JAMBPINVendingRequest;
import com.neptunesoftware.vtpassintegration.education.request.WAECResultCheckerRequest;
import com.neptunesoftware.vtpassintegration.education.response.JAMBPINVendingResponse;
import com.neptunesoftware.vtpassintegration.education.response.WAECResultCheckerResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JambPinVendingResponseMapper {


    private final Credentials credentials;

    public TransactionRequest mapPinVendingRequest(JAMBPINVendingRequest request, JAMBPINVendingResponse response) {
        return TransactionRequest.builder()
                .requestId(response.getRequestId())
                .channelName(credentials.getChannelName())
                .chargeAmount(response.getAmount())
//                .tranSender(response.getUniqueElement())
                .tranReceiver(request.getPhone())
                .isReversal("N")
                .narration(response.getTransaction().getProduct_name())
                .paymentCurr(credentials.getPaymentCurrency())
                .serviceId(request.getServiceID())
                .taxAmount(null)
                .tranStatus(response.getTransaction().getStatus())
                .tranType(response.getTransaction().getType())
                .tranAmount(response.getAmount().toString())
                .tranId(response.getTransaction().getTransactionId())
                .tranAppl(response.getTransaction().getPlatform())
                .tranMethod(response.getTransaction().getMethod())
                .tranPurpose(response.getResponse_description())
                .tranDate(Date.from(Instant.now()).toString())
                .build();
    }
}
