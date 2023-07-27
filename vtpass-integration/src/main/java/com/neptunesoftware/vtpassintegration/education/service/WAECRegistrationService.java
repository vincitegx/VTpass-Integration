package com.neptunesoftware.vtpassintegration.education.service;


import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.domain.WAECVariation;
import com.neptunesoftware.vtpassintegration.education.mapper.EducationPaymentResponseMapper;
import com.neptunesoftware.vtpassintegration.education.request.PurchaseProductRequest;
import com.neptunesoftware.vtpassintegration.education.request.ProductRegRequest;
import com.neptunesoftware.vtpassintegration.education.response.PurchaseProductResponse;

import com.neptunesoftware.vtpassintegration.education.response.ProductRegResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WAECRegistrationService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final EducationPaymentResponseMapper responseMapper;
    private final RequestIdGenerator requestIdGenerator;
//    private final RestTemplate restTemplate; // You need to configure RestTemplate

    public TransactionResponse purchaseWAECRegistration(ProductRegRequest request) {
        request.setRequest_id(requestIdGenerator.apply(4));
        String serviceId = "waec-registration"; // Replace with the actual service ID for WAEC registration
        String apiUrl = "https://sandbox.vtpass.com/api/pay"; // Replace with the actual API endpoint for purchasing WAEC registration

        // Perform the HTTP POST request to the VTpass API
        ProductRegResponse waecRegistrationResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ProductRegResponse.class)
                .block();

        // Map the VTpass response to the custom WAECRegistrationResponse
        TransactionRequest transactionRequest = responseMapper.mapRequest(request, waecRegistrationResponse);
        TransactionResponse transactionResponse = transactionService.saveTransaction(transactionRequest);

        return transactionResponse;
    }


    public List<WAECVariation> getVariationCodes() {
        // Implement the logic to call the API and return the list of WAECVariation
        return null;
    }

    public PurchaseProductResponse purchaseProduct(PurchaseProductRequest request) {
        // Implement the logic to call the API and return the PurchaseProductResponse
        return null;
    }

}