package com.neptunesoftware.vtpassintegration.electricity.mapper;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.electricity.request.ElectricBillRequest;
import com.neptunesoftware.vtpassintegration.electricity.response.ElectricBillResponse;
import com.neptunesoftware.vtpassintegration.electricity.response.ElectricBillResponseApi;
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

    public ElectricBillResponse mapToPostPaidResponse(ElectricBillRequest request, ElectricBillResponseApi<?> electricBillResponse) {
        return ElectricBillResponse.builder()
                .code(electricBillResponse.getElectricPostpaidResponse().getCode())
                .requestId(electricBillResponse.getElectricPostpaidResponse().getRequestId())
                .transactionId(electricBillResponse.getElectricPostpaidResponse().getContent().getTransactions().getTransactionId())
                .serviceId(request.getServiceID())
                .status(String.valueOf(electricBillResponse.getElectricPostpaidResponse().getContent().getTransactions().getStatus()))
                .description(electricBillResponse.getElectricPostpaidResponse().getResponse_description())
                .uniqueElement(electricBillResponse.getElectricPostpaidResponse().getContent().getTransactions().getUnique_element())
                .transactionAmount(String.valueOf(electricBillResponse.getElectricPostpaidResponse().getContent().getTransactions().getAmount()))
                .transactionType("Postpaid")
                .transactionPlatform(electricBillResponse.getElectricPostpaidResponse().getContent().getTransactions().getPlatform())
                .method(electricBillResponse.getElectricPostpaidResponse().getContent().getTransactions().getMethod())
                .productName(electricBillResponse.getElectricPostpaidResponse().getContent().getTransactions().getProduct_name())
                .phoneNumber(electricBillResponse.getElectricPostpaidResponse().getContent().getTransactions().getPhone())
                .build();
    }

    public ElectricBillResponse mapToPrepaidResponse(ElectricBillRequest request, ElectricBillResponseApi<?> electricBillResponse) {
        return ElectricBillResponse.builder()
                .code(electricBillResponse.getElectricPrepaidResponse().getCode())
                .requestId(electricBillResponse.getElectricPrepaidResponse().getRequestId())
                .transactionId(electricBillResponse.getElectricPrepaidResponse().getContent().getTransactions().getTransactionId())
                .serviceId(request.getServiceID())
                .status(String.valueOf(electricBillResponse.getElectricPrepaidResponse().getContent().getTransactions().getStatus()))
                .description(electricBillResponse.getElectricPrepaidResponse().getResponse_description())
                .uniqueElement(electricBillResponse.getElectricPrepaidResponse().getContent().getTransactions().getUnique_element())
                .transactionAmount(String.valueOf(electricBillResponse.getElectricPrepaidResponse().getContent().getTransactions().getAmount()))
                .transactionType("PrePaid")
                .transactionPlatform(electricBillResponse.getElectricPrepaidResponse().getContent().getTransactions().getPlatform())
                .method(electricBillResponse.getElectricPrepaidResponse().getContent().getTransactions().getMethod())
                .productName(electricBillResponse.getElectricPrepaidResponse().getContent().getTransactions().getProduct_name())
                .phoneNumber(electricBillResponse.getElectricPrepaidResponse().getContent().getTransactions().getPhone())
                .build();
    }
}
