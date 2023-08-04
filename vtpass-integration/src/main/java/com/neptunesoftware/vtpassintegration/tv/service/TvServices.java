package com.neptunesoftware.vtpassintegration.tv.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import com.neptunesoftware.vtpassintegration.tv.mapper.TvSubscriptionMapperResponse;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.tv.request.VerifySmartCardNumberRequest;
import com.neptunesoftware.vtpassintegration.tv.response.SubscriptionResponse;
import com.neptunesoftware.vtpassintegration.tv.response.TvSmartCardVerificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class TvServices {
private final WebClient.Builder webClient;
    private final RequestIdGenerator requestIdGenerator;
    private final TvSubscriptionMapperResponse responseMapper;
    private final TransactionService transactionService;
    private final Credentials credentials;

    public TransactionResponse tvSubscription(TvSubscriptionRequest  request) {
        TvSmartCardVerificationResponse verificationApiResponse = verifySmartCardNumber(
                new VerifySmartCardNumberRequest(request.getBillersCode(),
                request.getServiceID()));
        if (verificationApiResponse.getContent().getCustomerName()== null) {
            throw new TransactionException("INVALID SMART CARD NUMBER", "012", null);
        }
            request.setRequest_id(requestIdGenerator.apply(4));
            SubscriptionResponse response = webClient.build().post()
                    .uri(credentials.getBaseUrl() + "/api/pay")
                    .header("api-key", credentials.getApiKey())
                    .header("secret-key", credentials.getSecretKey())
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(SubscriptionResponse.class)
                    .block();
            if (!Objects.equals(response.getCode(), "000")) {
                throw new TransactionException(response.getResponseDescription(),response.getCode(),request.getRequest_id());
            }
        TransactionRequest transactionRequest = responseMapper.mapper(request, response);
            log.info(transactionRequest);
        TransactionResponse transactionResponse = transactionService.saveTransaction(transactionRequest);
            log.info("******** TRANSACTION SUCCESSFUL: RECORD INSERTED TO DB **********");
           return transactionResponse;
    }

    private TvSmartCardVerificationResponse verifySmartCardNumber(VerifySmartCardNumberRequest request) {
        return webClient.build().post()
                .uri(credentials.getBaseUrl()+"/api/merchant-verify")
                .header("api-key",credentials.getApiKey())
                .header("secret-key",credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TvSmartCardVerificationResponse.class)
                .block();
    }
}
