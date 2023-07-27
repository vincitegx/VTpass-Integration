package com.neptunesoftware.vtpassintegration.education.service;


import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.mapper.EducationPaymentResponseMapper;
import com.neptunesoftware.vtpassintegration.education.mapper.ResultCheckerResponseMapper;
import com.neptunesoftware.vtpassintegration.education.request.ProductRegRequest;
import com.neptunesoftware.vtpassintegration.education.request.WAECResultCheckerRequest;
import com.neptunesoftware.vtpassintegration.education.response.ProductRegResponse;
import com.neptunesoftware.vtpassintegration.education.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.education.response.WAECResultCheckerResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;

import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WAECResultCheckerService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final ResultCheckerResponseMapper resultCheckerResponseMapper;
    private final RequestIdGenerator requestIdGenerator;

    public Integer purchaseWAECResultChecker(WAECResultCheckerRequest request) {
        request.setRequest_id(requestIdGenerator.apply(4));
        String serviceId = "waec"; // Replace with the actual service ID for WAEC result checker
        String apiUrl = "https://sandbox.vtpass.com/api/pay"; // Replace with the actual API endpoint for purchasing WAEC result checker

        // Perform the HTTP POST request to the VTpass API
        WAECResultCheckerResponse waecResultCheckerResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(WAECResultCheckerResponse.class)
                .block();

        // Map the VTpass response to the custom WAECResultCheckerResponse
        TransactionRequest transactionRequest = resultCheckerResponseMapper.mapCheckerRequest(request, waecResultCheckerResponse);
        Integer transactionResponse = transactionService.saveTransaction(transactionRequest);

        return transactionResponse;
    }


}

//@Service
//@RequiredArgsConstructor
//public class WAECResultCheckerService {
//
//    private final Credentials credentials;
//    private final WebClient.Builder webClientBuilder;
//    private final TransactionService transactionService;
//    private final EducationPaymentResponseMapper responseMapper;
//    private final RequestIdGenerator requestIdGenerator;
//
//    public TransactionResponse purchaseWAECResultChecker(ProductRegRequest request) {
//        request.setRequest_id(requestIdGenerator.apply(4));
//        String serviceId = "waec"; // Replace with the actual service ID for WAEC result checker
//        String apiUrl = "https://sandbox.vtpass.com/api/pay"; // Replace with the actual API endpoint for purchasing WAEC result checker
//
//        // Perform the HTTP POST request to the VTpass API
//        ProductRegResponse productRegResponse = webClientBuilder.build().post()
//                .uri(apiUrl)
//                .header("api-key", credentials.getApiKey())
//                .header("secret-key", credentials.getSecretKey())
//                .bodyValue(request)
//                .retrieve()
//                .bodyToMono(ProductRegResponse.class)
//                .block();
//
//        // Map the VTpass response to the custom WAECResultCheckerResponse
//        TransactionRequest transactionRequest = responseMapper.mapRequest(request, productRegResponse);
//        TransactionResponse transactionResponse = transactionService.saveTransaction(transactionRequest);
//
//        return transactionResponse;
//    }
//}