package com.neptunesoftware.vtpassintegration.education.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.mapper.JambPinVendingResponseMapper;
import com.neptunesoftware.vtpassintegration.education.request.JAMBPINVendingRequest;
import com.neptunesoftware.vtpassintegration.education.response.JAMBPINVendingResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class JAMBPINVendingService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final JambPinVendingResponseMapper responseMapper;
    private final RequestIdGenerator requestIdGenerator;

    public Integer purchaseJAMBPINVending(JAMBPINVendingRequest request) {
        request.setRequest_id(requestIdGenerator.apply(4));
        String serviceId = "jamb"; // Replace with the actual service ID for JAMB PIN vending
        String apiUrl = "https://sandbox.vtpass.com/api/pay"; // Replace with the actual API endpoint for purchasing JAMB PIN vending

        // Perform the HTTP POST request to the VTpass API
        JAMBPINVendingResponse jambPINVendingResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JAMBPINVendingResponse.class)
                .block();

        // Map the VTpass response to the custom JAMBPINVendingResponse
        TransactionRequest transactionRequest = responseMapper.mapPinVendingRequest(request, jambPINVendingResponse);
        Integer transactionResponse = transactionService.saveTransaction(transactionRequest);

        return transactionResponse;
    }

}

