package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.mapper.HealthInsuranceMapper;
import com.neptunesoftware.vtpassintegration.insurance.request.HealthInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceResponse;
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
public class HealthInsuranceService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService service;
    private final HealthInsuranceMapper mapper;
    private final RequestIdGenerator requestIdGenerator;


    public TransactionResponse purchaseHealthInsurance(HealthInsuranceRequest request) {
        TransactionResponse transactionResponse;
        log.info("Purchasing Health Insurance product...");
        request.setRequest_id(requestIdGenerator.apply(4));
        String apiUrl = credentials.getBaseUrl()+"/api/pay";

        HealthInsuranceResponse healthInsuranceResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(HealthInsuranceResponse.class)
                .block();

        if (healthInsuranceResponse.getCode().equals("000")){
            TransactionRequest transactionRequest = mapper.mapRequest(request, healthInsuranceResponse);
            transactionResponse = service.saveTransaction(transactionRequest);
            log.info("TRANSACTION SUCCESSFUL, SAVED TO DATABASE...");
            return transactionResponse;
        }else {
            throw new TransactionException(healthInsuranceResponse.getResponseDescription(), healthInsuranceResponse.getCode(), healthInsuranceResponse.getRequestId());
        }

    }

}





