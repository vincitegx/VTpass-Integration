package com.neptunesoftware.vtpassintegration.insurance.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.request.ThirdPartyInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.ThirdPartyInsurance;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ThirdPartyInsuranceMapper {

    private final Credentials credentials;

    public TransactionRequest mapRequest(ThirdPartyInsuranceRequest request, ThirdPartyInsurance response) {

        return TransactionRequest.builder()
                .code(response.code())
                .requestId(response.requestId())
                .channelName(credentials.getChannelName())
                .chargeAmount(response.content().transactions().commission().toString())
                .tranSender(response.content().transactions().email())
                .tranReceiver(response.content().transactions().phone())
                .isReversal("N")
                .narration(response.content().transactions().product_name())
                .paymentCurr(credentials.getPaymentCurrency())
                .serviceId(request.getServiceID())
                .taxAmount(null)
                .tranStatus(response.content().transactions().status())
                .tranType(response.content().transactions().type())
                .tranAmount(response.content().transactions().amount().toString())
                .tranId(response.content().transactions().transactionId())
                .tranAppl(response.content().transactions().platform())
                .tranMethod(response.content().transactions().method())
                .tranPurpose(response.content().transactions().product_name())
                .tranDate(Date.from(Instant.now()).toString())
                .build();
    }
}
