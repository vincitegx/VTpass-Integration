package com.neptunesoftware.vtpassintegration.data.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.data.mapper.DataSubscriptionResponseMapper;
import com.neptunesoftware.vtpassintegration.data.request.DataSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.data.response.DataSubscriptionResponse;
import com.neptunesoftware.vtpassintegration.transaction.repository.TransactionRepository;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class DataSubscriptionService {
    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final DataSubscriptionResponseMapper mapper;
    private final RequestIdGenerator requestIdGenerator;

    public TransactionResponse subscribeForData(DataSubscriptionRequest dataSubscriptionRequest){
        dataSubscriptionRequest.setRequestId(requestIdGenerator.apply(4));
        DataSubscriptionResponse dataSubscriptionResponse = webClientBuilder.build().post()
                .uri("https://sandbox.vtpass.com/api/pay")
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(dataSubscriptionRequest)
                .retrieve()
                .bodyToMono(DataSubscriptionResponse.class)
                .block();
        TransactionRequest transactionRequest = mapper.apply(dataSubscriptionResponse);
        TransactionResponse transactionResponse = transactionService.saveTransaction(transactionRequest);
        return transactionResponse;
    }
}
