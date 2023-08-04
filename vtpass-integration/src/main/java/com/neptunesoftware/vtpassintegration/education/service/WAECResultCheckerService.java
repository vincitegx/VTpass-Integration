package com.neptunesoftware.vtpassintegration.education.service;


import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.education.mapper.ResultCheckerResponseMapper;
import com.neptunesoftware.vtpassintegration.education.request.WAECResultCheckerRequest;
import com.neptunesoftware.vtpassintegration.education.response.WAECResultCheckerResponse;
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
public class WAECResultCheckerService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final ResultCheckerResponseMapper resultCheckerResponseMapper;
    private final RequestIdGenerator requestIdGenerator;

    public TransactionResponse purchaseWAECResultChecker(WAECResultCheckerRequest request) {
        log.info("Purchasing Waec Result Checker product...");
        request.setRequest_id(requestIdGenerator.apply(4));
        String apiUrl = credentials.getBaseUrl()+"/api/pay";

        // Perform the HTTP POST request to the VTpass API
        WAECResultCheckerResponse waecResultCheckerResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(WAECResultCheckerResponse.class)
                .block();

        if (waecResultCheckerResponse.getCode().equals("000")){
            TransactionRequest transactionRequest = resultCheckerResponseMapper.mapCheckerRequest(request, waecResultCheckerResponse);
            log.info("TRANSACTION SUCCESSFUL, SAVED TO DATABASE...");
            return transactionService.saveTransaction(transactionRequest);
        }else {
            throw new TransactionException(waecResultCheckerResponse.getResponse_description(), waecResultCheckerResponse.getCode(), waecResultCheckerResponse.getRequestId());
        }

    }
}
