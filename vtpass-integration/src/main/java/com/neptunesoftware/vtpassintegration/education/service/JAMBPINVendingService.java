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
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Log4j2
public class JAMBPINVendingService {
    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final JambProductPurchaseResponseMapper responseMapper;
    private final RequestIdGenerator requestIdGenerator;

    public JAMBProfileVerificationResponse verifyJAMBProfile(JAMBProfileVerificationRequest request) {
        log.info("Verifying JAMB profile...");
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
        TransactionResponse transactionResponse;
        log.info("Purchasing JAMB product...");
        request.setRequest_id(requestIdGenerator.apply(4));
        String apiUrl = credentials.getBaseUrl()+"/api/pay";
        JAMBProfileVerificationRequest jambProfileVerificationRequest = new JAMBProfileVerificationRequest(request.getBillersCode(), request.getServiceID(), request.getVariation_code());
        JAMBProfileVerificationResponse jambProfileVerificationResponse = verifyJAMBProfile(jambProfileVerificationRequest);
        if(jambProfileVerificationResponse.content().Customer_Name().equals(null)){
            throw new TransactionException("Invalid Jamb Profile ID", null, null);
        }
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
            transactionResponse = transactionService.saveTransaction(transactionRequest);
            log.info("TRANSACTION SUCCESSFUL, SAVED TO DATABASE...");
            return transactionResponse;
        }else {
            throw new TransactionException(purchaseResponse.getCode(), purchaseResponse.getRequestId(), purchaseResponse.getPurchased_code());
        }

    }

}
