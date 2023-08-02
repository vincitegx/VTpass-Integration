package com.neptunesoftware.vtpassintegration.tv.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import com.neptunesoftware.vtpassintegration.tv.mapper.TvSubscriptionMapperResponse;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionStatusRequest;
import com.neptunesoftware.vtpassintegration.tv.request.VerifySmartCardNumberRequest;
import com.neptunesoftware.vtpassintegration.tv.response.TvSmartCardVerificationResponse;
import com.neptunesoftware.vtpassintegration.tv.response.TvSubscriptionResponseApi;
import com.neptunesoftware.vtpassintegration.tv.response.TvVariationResponseApi;
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
        TransactionResponse transactionResponse;
        TransactionRequest transactionRequest;
        TvSmartCardVerificationResponse verificationApiResponse = verifySmartCardNumber(
                new VerifySmartCardNumberRequest(request.getBillersCode(),
                request.getServiceID()));
        if (verificationApiResponse.getContent().getCustomerName()== null) {
            throw new TransactionException("INVALID SMART CARD NUMBER", "012", null);
        }
            request.setRequest_id(requestIdGenerator.apply(4));
            TvSubscriptionResponseApi response = webClient.build().post()
                    .uri(credentials.getBaseUrl() + "/api/pay")
                    .header("api-key", credentials.getApiKey())
                    .header("secret-key", credentials.getSecretKey())
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(TvSubscriptionResponseApi.class)
                    .block();
            assert response != null;
            if (!Objects.equals(response.getCode(), "000")) {
                throw new TransactionException(response.getResponseDescription(),response.getCode(),request.getRequest_id());
            }
             transactionRequest = responseMapper.mapper(request, response);
            log.info(transactionRequest);
             transactionResponse = transactionService.saveTransaction(transactionRequest);
            log.info("*****TRANSACTION IS SUCCESSFUL: DATA SAVED TO DATABASE **********");
           return transactionResponse;
    }
    public TransactionResponse tvSubscriptionStatus(TvSubscriptionStatusRequest request) {
        TvSubscriptionResponseApi response = webClient.build().post()
                    .uri(credentials.getBaseUrl() + "/api/requery") // Replace with your actual URL
                    .header("api-key", credentials.getApiKey())
                    .header("secret-key", credentials.getSecretKey())
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(TvSubscriptionResponseApi.class)
                    .block();
            assert response != null;
            if (!Objects.equals(response.getCode(), "000")) {
                throw new TransactionException(response.getResponseDescription(),response.getCode(),request.getRequest_id());
            }
            return mapTvToSubscriptionResponse(response);
    }
    public TvVariationResponseApi tvVariations(String serviceID) {
        TransactionResponse response;
        String jsonString = webClient.baseUrl(credentials.getBaseUrl() + "/api/service-variations").build()
                .get().uri(uriBuilder -> uriBuilder.queryParam("serviceID", serviceID).build())
                .header("api-key", credentials.getApiKey())
                .header("public-key", credentials.getPublicKey())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        assert jsonString != null;
        TvVariationResponseApi variationResponseApi = deserializeApiResponse(jsonString);
        log.info(variationResponseApi);
        if (!Objects.equals(variationResponseApi.getResponse_description(), "000")) {
           throw new TransactionException(variationResponseApi.getContent().getErrors(),variationResponseApi.getCode(),null);
        }
        return variationResponseApi;
    }
    private TvVariationResponseApi deserializeApiResponse(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(jsonString, TvVariationResponseApi.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TvVariationResponseApi();
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
    private  TransactionResponse mapTvToSubscriptionResponse(TvSubscriptionResponseApi responseApi) {
         return new TransactionResponse(responseApi.getCode(),responseApi.getContent().getTransactions().getStatus(),responseApi.getRequestId());
    }
}
