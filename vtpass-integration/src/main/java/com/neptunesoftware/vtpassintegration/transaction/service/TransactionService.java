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
        if(response == 1){
            return new TransactionResponse(transactionRequest.getCode(), transactionRequest.getTranStatus(), transactionRequest.getRequestId());
        }else {
            throw new TransactionException("Failed to save to DB", null, transactionRequest.getRequestId());
        }

    }

    public TransactionQueryResponse queryTransaction(String request_id) {
        TransactionQueryResponse response = TransactionQueryResponse.builder().build();
        response = webClientBuilder.build()
                .post()
                .uri("https://sandbox.vtpass.com/api/requery")
                .header("api-key",credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(new RequestId(request_id))
                .retrieve()
                .bodyToMono(TransactionQueryResponse.class)
                .block();
        if(response.code() == "000"){
            return response;
        }else {
            throw new TransactionException(response.response_description(), response.code(), request_id);
        }
    }

    public CallBackResponse callBack(CallBackRequest callBackRequest){
        int response;
        if(callBackRequest.type().equals("transaction-update")){
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
        if(callBackRequest.data().content().transactions().status().equals("reversed")){
            transactionRequest = TransactionRequest.builder()
                    .code(callBackRequest.data().code())
                    .requestId(callBackRequest.requestId())
                    .isReversal("Y")
                    .tranStatus(callBackRequest.data().content().transactions().status())
                    .tranMethod(callBackRequest.data().content().transactions().method())
                    .tranId(callBackRequest.data().content().transactions().transactionId())
                    .build();
        }else if(callBackRequest.data().content().transactions().status().equals("delivered")){
            transactionRequest = TransactionRequest.builder()
                    .requestId(callBackRequest.requestId())
                    .isReversal("N")
                    .tranStatus(callBackRequest.data().content().transactions().status())
                    .tranMethod(callBackRequest.data().content().transactions().method())
                    .tranId(callBackRequest.data().content().transactions().transactionId())
                    .build();
        }
        return transactionRepository.updateTransaction(transactionRequest);
    }
}

