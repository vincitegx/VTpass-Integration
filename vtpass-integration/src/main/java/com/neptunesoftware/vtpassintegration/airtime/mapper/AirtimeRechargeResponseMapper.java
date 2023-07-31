package com.neptunesoftware.vtpassintegration.airtime.mapper;
import com.neptunesoftware.vtpassintegration.airtime.request.AirtimeRequest;
import com.neptunesoftware.vtpassintegration.airtime.request.PurchaseIntlProductsRequest;
import com.neptunesoftware.vtpassintegration.airtime.response.AirtimeResponse;
import com.neptunesoftware.vtpassintegration.airtime.response.PurchaseIntlProductsResponse;
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




    public TransactionRequest applyMap(PurchaseIntlProductsResponse purchaseIntlProductsResponse, PurchaseIntlProductsRequest purchaseIntlProductsRequest) {
        return   TransactionRequest.builder()
                .requestId(purchaseIntlProductsRequest.getRequest_id())
                .tranAmount(purchaseIntlProductsRequest.getAmount())
                .tranDate(Date.from(Instant.now()).toString())
                .channelName(credentials.getChannelName())
                .chargeAmount(purchaseIntlProductsResponse.content().transactions().commission().toString())
                .tranSender(purchaseIntlProductsResponse.content().transactions().unique_element())
                .tranReceiver(purchaseIntlProductsResponse.content().transactions().phone())
                .isReversal("N")
                .narration(purchaseIntlProductsResponse.content().transactions().product_name())
                .paymentCurr(credentials.getPaymentCurrency())
                .serviceId(purchaseIntlProductsRequest.getServiceID())
                .taxAmount(purchaseIntlProductsResponse.content().transactions().total_amount().toString())
                .tranStatus(purchaseIntlProductsResponse.content().transactions().status())// we will refactor this one later, remind me
                .tranType(purchaseIntlProductsResponse.content().transactions().type())
                .tranId(purchaseIntlProductsResponse.content().transactions().transactionId())
                .tranAppl(purchaseIntlProductsResponse.content().transactions().platform())
                .tranMethod(purchaseIntlProductsResponse.content().transactions().method())
                .tranPurpose(purchaseIntlProductsResponse.content().transactions().product_name())//
                .build();
    }




































}
