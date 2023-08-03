package com.neptunesoftware.vtpassintegration.education.service;


import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.mapper.EducationPaymentResponseMapper;
import com.neptunesoftware.vtpassintegration.education.request.ProductRegRequest;
import com.neptunesoftware.vtpassintegration.education.response.ProductRegResponse;
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
public class WAECRegistrationService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final EducationPaymentResponseMapper responseMapper;
    private final RequestIdGenerator requestIdGenerator;

    public TransactionResponse purchaseWAECRegistration(ProductRegRequest request) {
        log.info("Purchasing Waec Registration product...");
        request.setRequest_id(requestIdGenerator.apply(4));
        String apiUrl = credentials.getBaseUrl() + "/api/pay";

        // Perform the HTTP POST request to the VTpass API
        ProductRegResponse waecRegistrationResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ProductRegResponse.class)
                .block();

            if (waecRegistrationResponse.getCode().equals("000")){
                TransactionRequest transactionRequest = responseMapper.mapRequest(request, waecRegistrationResponse);
                log.info("TRANSACTION SUCCESSFUL...");
                return transactionService.saveTransaction(transactionRequest);
            }else {
                throw new TransactionException(waecRegistrationResponse.getResponse_description(), waecRegistrationResponse.getCode(), waecRegistrationResponse.getRequestId());
            }
    }

}