package com.neptunesoftware.vtpassintegration.transaction.service;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.repository.TransactionRepository;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionQueryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final Credentials credentials ;
    private final TransactionRepository transactionRepository;
    private final WebClient.Builder webClientBuilder;
    public int saveTransaction(TransactionRequest transactionRequest){
        return transactionRepository.saveTransaction(transactionRequest);
    }

    public TransactionQueryResponse queryTransaction(String request_id) {
        System.out.println("entered controller method "+ request_id);

        return webClientBuilder.build()
                .post()
                .uri("https://sandbox.vtpass.com/api/requery")
                .header("api-key",credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(new RequestId(request_id))
                .retrieve()
                .bodyToMono(TransactionQueryResponse.class)
                .block();
    }
}
@Data
@AllArgsConstructor
class RequestId{
    private String request_id;
}
