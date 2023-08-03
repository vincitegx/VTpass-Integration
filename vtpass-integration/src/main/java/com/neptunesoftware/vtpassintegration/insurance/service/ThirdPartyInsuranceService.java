package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.mapper.ThirdPartyInsuranceMapper;
import com.neptunesoftware.vtpassintegration.insurance.request.ThirdPartyInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.ThirdPartyInsurance;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Log4j2
public class ThirdPartyInsuranceService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService service;
    private final ThirdPartyInsuranceMapper mapper;
    private final RequestIdGenerator requestIdGenerator;
    public TransactionResponse purchaseProduct(ThirdPartyInsuranceRequest request) {
        log.info("Purchasing Third party Insurance product...");
        request.setRequest_id(requestIdGenerator.apply(4));
        String apiUrl = credentials.getBaseUrl()+"/api/pay";
        System.out.println(request);
        //perform the HTTP request to the VTpass API
        ThirdPartyInsurance insuranceResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ThirdPartyInsurance.class)
                .block();
        System.out.println(insuranceResponse);

        if (insuranceResponse.code().equals("000")){
            TransactionRequest transactionRequest = mapper.mapRequest(request, insuranceResponse);
            log.info("TRANSACTION SUCCESSFUL, SAVED TO DATABASE...");
            return service.saveTransaction(transactionRequest);
        }else {
            throw new TransactionException(insuranceResponse.code(), insuranceResponse.response_description(), insuranceResponse.requestId());
        }

    }



}