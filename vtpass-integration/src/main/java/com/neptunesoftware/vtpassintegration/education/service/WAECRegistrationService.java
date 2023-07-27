package com.neptunesoftware.vtpassintegration.education.service;


import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.mapper.EducationPaymentResponseMapper;
import com.neptunesoftware.vtpassintegration.education.request.ProductRegRequest;
import com.neptunesoftware.vtpassintegration.education.response.ProductRegResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WAECRegistrationService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final EducationPaymentResponseMapper responseMapper;
    private final RequestIdGenerator requestIdGenerator;

        public Integer purchaseWAECRegistration(ProductRegRequest request) {
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
            System.out.println(waecRegistrationResponse);
        // Map the VTpass response to the custom WAECRegistrationResponse
        TransactionRequest transactionRequest = responseMapper.mapRequest(request, waecRegistrationResponse);
        Integer transactionResponse = transactionService.saveTransaction(transactionRequest);

        return transactionResponse;
    }


}