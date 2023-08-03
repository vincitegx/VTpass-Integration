//package com.neptunesoftware.vtpassintegration.insurance.mapper;
//
//import com.neptunesoftware.vtpassintegration.config.Credentials;
//
//import com.neptunesoftware.vtpassintegration.insurance.request.ThirdPartyInsuranceRequest;
//import com.neptunesoftware.vtpassintegration.insurance.response.InsuranceResponse;
//import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//import java.util.Date;
//
//@Component
//@RequiredArgsConstructor
//public class InsuranceMapper {
//
//    private final Credentials credentials;
//    public TransactionRequest mapRequest(ThirdPartyInsuranceRequest dataSubscriptionRequest, InsuranceResponse insuranceResponse) {
//        return TransactionRequest.builder()
//                .code(insuranceResponse.code())
//                .requestId(insuranceResponse.requestId())
//                .channelName(credentials.getChannelName())
//                .chargeAmount(insuranceResponse.content().transactions().commission().toString())
//                .tranSender(insuranceResponse.content().transactions().unique_element())
//                .tranReceiver(insuranceResponse.content().transactions().phone())
//                .isReversal("N")
//                .narration(insuranceResponse.content().transactions().product_name())
//                .paymentCurr(credentials.getPaymentCurrency())
//                .serviceId(dataSubscriptionRequest.getServiceID())
//                .taxAmount(null)
//                .tranStatus(insuranceResponse.content().transactions().status())
//                .tranType(insuranceResponse.content().transactions().type())
//                .tranAmount(insuranceResponse.content().transactions().total_amount().toString())
//                .tranId(insuranceResponse.content().transactions().transactionId())
//                .tranAppl(insuranceResponse.content().transactions().platform())
//                .tranMethod(insuranceResponse.content().transactions().method())
//                .tranPurpose(insuranceResponse.content().transactions().product_name())
//                .tranDate(Date.from(Instant.now()).toString())
//                .build();
//    }
//}
