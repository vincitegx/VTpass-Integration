package com.neptunesoftware.vtpassintegration.tv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import com.neptunesoftware.vtpassintegration.tv.response.TvSubscriptionResponseApi;
import com.neptunesoftware.vtpassintegration.tv.domain.TvVariationFromApi;
import com.neptunesoftware.vtpassintegration.tv.mapper.TvSubscriptionMapperResponse;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionStatusRequest;
import com.neptunesoftware.vtpassintegration.tv.request.VerifySmartCardNumberRequest;
import com.neptunesoftware.vtpassintegration.tv.response.*;
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
    private static final String VT_PASS_BASE_URL = "https://sandbox.vtpass.com/api";

    public TransactionResponse tvSubscription(TvSubscriptionRequest  request) {
        TvSmartCardVerificationResponse verificationApiResponse = verifySmartCardNumber(
                new VerifySmartCardNumberRequest(request.getBillersCode(),
                request.getServiceID()));
        if (verificationApiResponse.getContent().getCustomerName()== null) {
            throw new RuntimeException("");
        }
        request.setRequest_id(requestIdGenerator.apply(4));
        TvSubscriptionResponseApi response = webClient.build().post()
                .uri("https://sandbox.vtpass.com/api/pay") 
                .header("api-key",credentials.getApiKey())
                .header("secret-key",credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TvSubscriptionResponseApi.class)
                .block();
        assert response != null;
        if (response.getCode() != "000") {
            throw new RuntimeException("");
        }
        log.info("RequestId {}",response.getRequestId());
        TransactionRequest transactionRequest = responseMapper.mapper(request,response);
        TransactionResponse transactionResponse =  transactionService.saveTransaction(transactionRequest);
        log.info("*****TRANSACTION IS SUCCESSFUL, DATA SAVED TO DATABASE **********");
        return transactionResponse;
    }

    private TvSmartCardVerificationResponse verifySmartCardNumber(VerifySmartCardNumberRequest request) {
        return webClient.build().post()
                .uri(VT_PASS_BASE_URL+"/merchant-verify")
                .header("api-key",credentials.getApiKey())
                .header("secret-key",credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TvSmartCardVerificationResponse.class)
                .block();
    }
    
    public TvSubscriptionStatusResponse tvSubscriptionStatus(TvSubscriptionStatusRequest request) {
        TvSubscriptionResponseApi response = webClient.build().post()
                .uri(VT_PASS_BASE_URL+"/requery") // Replace with your actual URL
                .header("api-key",credentials.getApiKey())
                .header("secret-key",credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TvSubscriptionResponseApi.class)
                .block();
        assert response != null;
        log.info("Response TvSubscription message {}",response);

        return TvSubscriptionStatusResponse.builder()
                .status(response.getContent().getTransactions().getStatus())
                .productName(response.getContent().getTransactions().getProductName()).build();
    }

    public TvVariationResponseApi tvVariations(String serviceID) {
        String jsonString = webClient.baseUrl(VT_PASS_BASE_URL+"/service-variations").build()
                .get().uri( uriBuilder -> uriBuilder.queryParam("serviceID", serviceID).build())
                .header("api-key", credentials.getApiKey())
                .header("public-key", credentials.getPublicKey())
                .retrieve()
                .bodyToMono(String.class)
                .block();
             TvVariationFromApi responseFromApi = deserializeAPIResponse(jsonString);
             log.info(responseFromApi);
        assert jsonString != null;
        log.info("Response: {}", jsonString);
        assert responseFromApi != null;
        return mapTvVariationFromApiToResponse(responseFromApi);
    }

    private static TvVariationFromApi deserializeAPIResponse(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, TvVariationFromApi.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static TvVariationResponseApi mapTvVariationFromApiToResponse(TvVariationFromApi tvVariationFromApi) {
        return TvVariationResponseApi.builder()
                .serviceName(tvVariationFromApi.getContent().getServiceName())
                .convenienceFee(tvVariationFromApi.getContent().getConvinience_fee())
                .variations(tvVariationFromApi.getContent().getVarations())
                .build();
    }
}
