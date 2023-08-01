package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.mapper.ThirdPartyInsuranceMapper;
import com.neptunesoftware.vtpassintegration.insurance.request.ThirdPartyInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.ThirdPartyInsuranceResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ThirdPartyInsuranceService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService service;
    private final ThirdPartyInsuranceMapper mapper;
    private final RequestIdGenerator requestIdGenerator;
    public TransactionResponse purchaseProduct(ThirdPartyInsuranceRequest request) {

        request.setRequestId(requestIdGenerator.apply(4));
        String serviceId = "ui-insure";
        String apiUrl = "https://sandbox.vtpass.com/api/pay";

        //perform the HTTP request to the VTpass API
        ThirdPartyInsuranceResponse thirdPartyInsuranceResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ThirdPartyInsuranceResponse.class)
                .block();
        System.out.println(thirdPartyInsuranceResponse);

        // Map the VTpass response to the custom ThirdPartyInsuranceResponse
        TransactionRequest transactionRequest = mapper.mapRequest(request, thirdPartyInsuranceResponse);
        TransactionResponse transResponse = service.saveTransaction(transactionRequest);

        return transResponse;


    }



}