package com.neptunesoftware.vtpassintegration.education.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.mapper.JambProductPurchaseResponseMapper;
import com.neptunesoftware.vtpassintegration.education.request.JAMBProductPurchaseRequest;
import com.neptunesoftware.vtpassintegration.education.request.JAMBProfileVerificationRequest;
import com.neptunesoftware.vtpassintegration.education.response.JAMBProductPurchaseResponse;
import com.neptunesoftware.vtpassintegration.education.response.JAMBProfileVerificationResponse;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
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
    private final JambProductPurchaseResponseMapper responseMapper;
    private final RequestIdGenerator requestIdGenerator;

    public JAMBProfileVerificationResponse verifyJAMBProfile(JAMBProfileVerificationRequest request) {
        String apiUrl = credentials.getBaseUrl()+ "/api/merchant-verify";

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

    public TransactionResponse purchaseJAMBProduct(JAMBProductPurchaseRequest request) {
        request.setRequest_id(requestIdGenerator.apply(4));
        String apiUrl = credentials.getBaseUrl()+"/api/pay";

        JAMBProductPurchaseResponse purchaseResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JAMBProductPurchaseResponse.class)
                .block();

        if (purchaseResponse.getCode().equals("000")){
            TransactionRequest transactionRequest = responseMapper.mapPinVendingRequest(request, purchaseResponse);
            return transactionService.saveTransaction(transactionRequest);
        }else {
            throw new TransactionException(purchaseResponse.getCode(), purchaseResponse.getRequestId(), purchaseResponse.getPurchased_code());
        }

    }


}
