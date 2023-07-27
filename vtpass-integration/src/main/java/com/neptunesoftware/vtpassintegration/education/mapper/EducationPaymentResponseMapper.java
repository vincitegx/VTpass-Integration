package com.neptunesoftware.vtpassintegration.education.mapper;


import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.request.ProductRegRequest;

import com.neptunesoftware.vtpassintegration.education.response.ProductRegResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationPaymentResponseMapper {

    private final Credentials credentials;

    public TransactionRequest mapRequest(ProductRegRequest request, ProductRegResponse waecRegResponse) {

        return TransactionRequest.builder()
                .requestId(request.getRequest_id())
                .serviceId(request.getServiceID())
                .tranAmount(waecRegResponse.getAmount())
                //variation_code
                .tranAmount(waecRegResponse.getAmount().toString())
                // quantity
                .tranReceiver(request.getPhone())

                //////////////////////////////////////////////////////////////////
                .tranType(waecRegResponse.getContent().getTransaction().getType())
                .tranId(waecRegResponse.getContent().getTransaction().getTransactionId())
                .tranAppl(waecRegResponse.getContent().getTransaction().getPlatform())
                .tranMethod(waecRegResponse.getContent().getTransaction().getMethod())
                .channelName(credentials.getChannelName())

                .build();
    }

}
