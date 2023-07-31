package com.neptunesoftware.vtpassintegration.insurance.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.request.PersonalAccidentInsurancePurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.PersonalAccidentInsuranceResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class PersonalAccidentInsuranceMapper {

    private final Credentials credentials;

    public TransactionRequest mapper(PersonalAccidentInsurancePurchaseRequest request, PersonalAccidentInsuranceResponse response){

        return TransactionRequest.builder()
                .requestId(request.getRequest_id())
                .channelName(credentials.getChannelName())
                .chargeAmount(response.getContent().getTransaction().getCommission().toString())
                .tranSender(response.getContent().getTransaction().getEmail())
                .tranReceiver(response.getContent().getTransaction().getPhone())
                .isReversal("N")
                .narration(response.getContent().getTransaction().getProductName())
                .paymentCurr(credentials.getPaymentCurrency())
                .serviceId(request.getServiceID())
                .taxAmount(null)
                .tranStatus(response.getContent().getTransaction().getStatus())
                .tranType(response.getContent().getTransaction().getType())
                .tranAmount(response.getContent().getTransaction().getAmount().toString())
                .tranId(response.getContent().getTransaction().getTransactionId())
                .tranAppl(response.getContent().getTransaction().getPlatform())
                .tranMethod(response.getContent().getTransaction().getMethod())
                .tranPurpose(response.getContent().getTransaction().getProductName())
                .tranDate(Date.from(Instant.now()).toString())
                .build();
    }

}
