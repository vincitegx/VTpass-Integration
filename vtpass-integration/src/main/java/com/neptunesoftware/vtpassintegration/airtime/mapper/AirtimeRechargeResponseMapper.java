package com.neptunesoftware.vtpassintegration.airtime.mapper;
import com.neptunesoftware.vtpassintegration.airtime.request.AirtimeRequest;
import com.neptunesoftware.vtpassintegration.airtime.response.AirtimeResponse;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@AllArgsConstructor
public class AirtimeRechargeResponseMapper {
    private final Credentials credentials;
    public TransactionRequest apply(AirtimeResponse airtimeResponse, AirtimeRequest airtimeRequest) {
    return     TransactionRequest.builder()
                .requestId(airtimeResponse.getRequestId())
                        .tranAmount(airtimeResponse.getAmount())
                        .tranDate(Date.from(Instant.now()).toString())
                        .channelName(credentials.getChannelName())
                        .chargeAmount(null)
                        .tranSender(credentials.getPhoneNumber())
                        .tranReceiver(airtimeRequest.getPhone())
                        .isReversal("N")
                        .narration(airtimeRequest.getServiceID())
                        .paymentCurr(credentials.getPaymentCurrency())
                        .serviceId(airtimeRequest.getServiceID())
                        .taxAmount(null)
                        .tranStatus(airtimeResponse.getCode())// we will refactor this one later, remind me
                        .tranType(null)
                        .tranId(airtimeRequest.getRequest_id())
                        .tranAppl("api")
                        .tranMethod(null)
                        .tranPurpose(null)
                        .tranDate(Date.from(Instant.now()).toString())
                        .build();
    }
}
