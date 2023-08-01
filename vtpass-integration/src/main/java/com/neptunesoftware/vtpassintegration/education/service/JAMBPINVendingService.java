package com.neptunesoftware.vtpassintegration.education.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.mapper.JambProductPurchaseResponseMapper;
import com.neptunesoftware.vtpassintegration.education.request.JAMBProductPurchaseRequest;
import com.neptunesoftware.vtpassintegration.education.request.JAMBProfileVerificationRequest;
import com.neptunesoftware.vtpassintegration.education.response.JAMBProductPurchaseResponse;
import com.neptunesoftware.vtpassintegration.education.response.JAMBProfileVerificationResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class JAMBPINVendingService {
    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final JambProductPurchaseResponseMapper responseMapper;
    private final RequestIdGenerator requestIdGenerator;

    public JAMBProfileVerificationResponse verifyJAMBProfile(JAMBProfileVerificationRequest request) {
        String apiUrl = "https://sandbox.vtpass.com/api/merchant-verify";

        JAMBProfileVerificationResponse verificationResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JAMBProfileVerificationResponse.class)
                .block();

        return verificationResponse;
    }

    public JAMBProductPurchaseResponse purchaseJAMBProduct(JAMBProductPurchaseRequest request) {
//        request.setServiceID("jamb");
        String apiUrl = "https://sandbox.vtpass.com/api/pay";

        JAMBProductPurchaseResponse purchaseResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JAMBProductPurchaseResponse.class)
                .block();

        // Map the VTpass response to the custom JAMBProductPurchaseResponse
        TransactionRequest transactionRequest = responseMapper.mapPinVendingRequest(request, purchaseResponse);
        TransactionResponse transactionResponse = transactionService.saveTransaction(transactionRequest);

        return purchaseResponse;
    }

//    public JAMBProductPurchaseResponse queryTransactionStatus(String requestId) {
//        String apiUrl = "https://api-service.vtpass.com/api/requery";
//
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("request_id", requestId);
//
//        JAMBProductPurchaseResponse queryResponse = webClientBuilder.build().post()
//                .uri(apiUrl)
//                .header("api-key", credentials.getApiKey())
//                .header("secret-key", credentials.getSecretKey())
//                .bodyValue(body)
//                .retrieve()
//                .bodyToMono(JAMBProductPurchaseResponse.class)
//                .block();
//
//        return queryResponse;
//    }
}
