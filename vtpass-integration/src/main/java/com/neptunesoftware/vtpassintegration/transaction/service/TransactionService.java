package com.neptunesoftware.vtpassintegration.transaction.service;

import com.neptunesoftware.vtpassintegration.data.response.DataSubscriptionResponse;
import com.neptunesoftware.vtpassintegration.transaction.repository.TransactionRepository;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WebClient.Builder webClientBuilder;

    public int saveTransaction(TransactionRequest transactionRequest){
        return transactionRepository.saveTransaction(transactionRequest);
    }

    public DataSubscriptionResponse queryTransaction(String requestId) {
        System.out.println("entered controller method "+ requestId);
        return webClientBuilder.build()
                .post()
                .uri(" https://sandbox.vtpass.com/api/requery")
                .bodyValue(requestId)
                .retrieve()
                .bodyToMono(DataSubscriptionResponse.class)
                .block();
    }
}
