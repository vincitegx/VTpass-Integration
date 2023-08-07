package com.neptunesoftware.vtpassintegration.electricity.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.electricity.request.ElectricBillRequest;
import com.neptunesoftware.vtpassintegration.electricity.response.ElectricBillResponse;
import com.neptunesoftware.vtpassintegration.electricity.response.ElectricityPaymentResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class ElectricityMapper {
    private final Credentials credentials;
    public TransactionRequest  mapperToDB(ElectricBillResponse electricBillResponse) {
        return TransactionRequest.builder()
                .code(electricBillResponse.getCode())
                .requestId(electricBillResponse.getRequestId())
                .serviceId(electricBillResponse.getServiceId())
                .tranSender(electricBillResponse.getUniqueElement())
                .tranReceiver(electricBillResponse.getPhoneNumber())
                .tranAmount(electricBillResponse.getTransactionAmount())
                .isReversal("N")
                .tranAppl(electricBillResponse.getTransactionPlatform())
                .taxAmount(electricBillResponse.getTaxAmount())
                .channelName(credentials.getChannelName())
                .paymentCurr(credentials.getPaymentCurrency())
                .tranPurpose(electricBillResponse.getProductName())
                .tranStatus(electricBillResponse.getStatus())
                .tranType(electricBillResponse.getTransactionType())
                .tranId(electricBillResponse.getTransactionId())
                .tranAppl(electricBillResponse.getTransactionPlatform())
                .narration(electricBillResponse.getDescription())
                .tranDate(Date.from(Instant.now()).toString())
                .tranMethod(electricBillResponse.getMethod())
                .build();
    }

    public ElectricBillResponse mapToElectricBillResponse(ElectricBillRequest request, ElectricityPaymentResponse electricBillResponse) {
        String meterType = null;
        if (request.getVariation_code().equalsIgnoreCase("prepaid"))
            meterType = " prepaid";
        if (request.getVariation_code().equalsIgnoreCase("postpaid"))
            meterType = " postpaid";

        return ElectricBillResponse.builder()
                .code(electricBillResponse.getCode())
                .requestId(electricBillResponse.getRequestId())
                .transactionId(electricBillResponse.getContent().getTransactions().getTransactionId())
                .serviceId(request.getServiceID())
                .status(String.valueOf(electricBillResponse.getContent().getTransactions().getStatus()))
                .description(electricBillResponse.getContent().getTransactions().getProduct_name()+meterType)
                .uniqueElement(electricBillResponse.getContent().getTransactions().getUnique_element())
                .transactionAmount(String.valueOf(electricBillResponse.getContent().getTransactions().getAmount()))
                .transactionType(request.getVariation_code())
                .transactionPlatform(electricBillResponse.getContent().getTransactions().getPlatform())
                .method(electricBillResponse.getContent().getTransactions().getMethod())
                .productName(electricBillResponse.getContent().getTransactions().getProduct_name())
                .phoneNumber(electricBillResponse.getContent().getTransactions().getPhone())
                .build();
    }
}
