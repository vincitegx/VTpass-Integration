package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.mapper.HomeCoverInsuranceResponseMapper;
import com.neptunesoftware.vtpassintegration.insurance.request.HomeCoverPurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.HomeCoverPurchaseResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.InsuranceExtraFieldsResponse;
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
public class HomeCoverInsuranceService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final HomeCoverInsuranceResponseMapper responseMapper;
    private final RequestIdGenerator requestIdGenerator;

    public InsuranceExtraFieldsResponse extraFields(String serviceID) {
        try {
            log.info("Fetching extra fields for Insurance...");
            return webClientBuilder.build().get()
                    .uri(credentials.getBaseUrl() + "/api/extra-fields",
                            uriBuilder -> uriBuilder.queryParam("serviceID", serviceID)
                                    .build())
                    .header("api-key", credentials.getApiKey())
                    .header("secret-key", credentials.getSecretKey())
                    .retrieve()
                    .bodyToMono(InsuranceExtraFieldsResponse.class)
                    .block();
        } catch (Exception ex) {
            throw new TransactionException("ERROR, EXTRA FIELDS DOES NOT EXIST", "012", null);
        }
    }


    // Method to purchase Home Cover Insurance
    public TransactionResponse purchaseHomeCoverInsurance(HomeCoverPurchaseRequest request) {
        log.info("Purchasing Home cover Insurance product...");
        request.setRequest_id(requestIdGenerator.apply(4));
        String apiUrl = credentials.getBaseUrl()+"/api/pay";

        // Perform the HTTP POST request to the VTpass API
        HomeCoverPurchaseResponse purchaseResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(HomeCoverPurchaseResponse.class)
                .block();

        if (purchaseResponse.getCode().equals("000")) {
            TransactionRequest transactionRequest = responseMapper.mapRequest(request, purchaseResponse);
            log.info("TRANSACTION SUCCESSFUL, SAVED TO DATABASE....");
            return transactionService.saveTransaction(transactionRequest);
        } else {
            throw new TransactionException(purchaseResponse.getResponse_description(), purchaseResponse.getCode(), purchaseResponse.getRequestId());
        }
    }

}

