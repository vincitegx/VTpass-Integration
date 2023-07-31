package com.neptunesoftware.vtpassintegration.transaction.service;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.repository.TransactionRepository;
import com.neptunesoftware.vtpassintegration.transaction.request.CallBackRequest;
import com.neptunesoftware.vtpassintegration.transaction.request.RequestId;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.CallBackResponse;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionQueryResponse;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
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
    public TransactionResponse saveTransaction(TransactionRequest transactionRequest){
        int response = transactionRepository.saveTransaction(transactionRequest);
        return new TransactionResponse(transactionRequest.getCode(), transactionRequest.getTranStatus(), transactionRequest.getRequestId());
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

    public CallBackResponse callBack(CallBackRequest callBackRequest){
        int response = 0;
        if(callBackRequest.type() == "transaction-update"){
            response = updateTransactionInDB(callBackRequest);
            if(response == 1){
                return new CallBackResponse("success");
            }else {
                return new CallBackResponse("fail");
            }
        }else {
            throw new TransactionException("Invalid WebHook Type", null, null);
        }
    }

    private int updateTransactionInDB(CallBackRequest callBackRequest) {
        TransactionRequest transactionRequest = TransactionRequest.builder().build();
        if(callBackRequest.content().transactions().status() == "reversed"){
            transactionRequest = TransactionRequest.builder()
                    .requestId(callBackRequest.requestId())
                    .isReversal("Y")
                    .tranStatus(callBackRequest.content().transactions().status())
                    .tranMethod(callBackRequest.content().transactions().method())
                    .tranId(callBackRequest.content().transactions().transactionId())
                    .build();
        }else if(callBackRequest.content().transactions().status() == "delivered"){
            transactionRequest = TransactionRequest.builder()
                    .requestId(callBackRequest.requestId())
                    .isReversal("N")
                    .tranStatus(callBackRequest.content().transactions().status())
                    .tranMethod(callBackRequest.content().transactions().method())
                    .tranId(callBackRequest.content().transactions().transactionId())
                    .build();
        }
        return transactionRepository.updateTransaction(transactionRequest);
    }
}

