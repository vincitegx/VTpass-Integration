package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceContent;
import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceExtraField;
import com.neptunesoftware.vtpassintegration.insurance.mapper.HomeCoverInsuranceResponseMapper;
import com.neptunesoftware.vtpassintegration.insurance.request.HomeCoverPurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.HomeCoverOptionResponse;
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

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class HomeCoverInsuranceService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final HomeCoverInsuranceResponseMapper responseMapper;
    private final RequestIdGenerator requestIdGenerator;

    public InsuranceExtraFieldsResponse extraFields() {
            log.info("Fetching extra fields for Home Cover Insurance...");
            String apiUrl = credentials.getBaseUrl() + "/api/extra-fields?serviceID=home-cover-insurance";

            // Perform the HTTP GET request to the VTpass API
            InsuranceExtraFieldsResponse extraFieldsResponse = webClientBuilder.build().get()
                    .uri(apiUrl)
                    .header("api-key", credentials.getApiKey())
                    .header("secret-key", credentials.getSecretKey())
                    .retrieve()
                    .bodyToMono(InsuranceExtraFieldsResponse.class)
                    .block();

            if (extraFieldsResponse != null && "000".equals(extraFieldsResponse.getResponseDescription())) {
                return extraFieldsResponse;
            } else {
                throw new RuntimeException("Failed to fetch extra fields for Home Cover Insurance");
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
            log.info("Transaction Successful, saved to Database...");
            return transactionService.saveTransaction(transactionRequest);
        } else {
            throw new TransactionException(purchaseResponse.getResponse_description(), purchaseResponse.getCode(), purchaseResponse.getRequestId());
        }
    }

}

