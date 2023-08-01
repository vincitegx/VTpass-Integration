package com.neptunesoftware.vtpassintegration.data.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.data.mapper.DataSubscriptionResponseMapper;
import com.neptunesoftware.vtpassintegration.data.request.DataSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.data.request.SmileVerificationRequest;
import com.neptunesoftware.vtpassintegration.data.response.DataSubscriptionResponse;
import com.neptunesoftware.vtpassintegration.data.response.SmileVerificationResponse;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
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
    private final SmileVerificationService smileVerificationService;

    public TransactionResponse subscribeForData(DataSubscriptionRequest dataSubscriptionRequest){
        if(dataSubscriptionRequest.getServiceID() == "smile-direct"){
            SmileVerificationResponse smileVerificationResponse =
                    smileVerificationService.verifySmileEmail(new SmileVerificationRequest(dataSubscriptionRequest.getBillersCode(), dataSubscriptionRequest.getServiceID()));
            if(smileVerificationResponse.content().Customer_Name() == null){
                throw new TransactionException("Invalid Billers Code !!!", null, null);
            }
        }
        dataSubscriptionRequest.setRequest_id(requestIdGenerator.apply(4));
        DataSubscriptionResponse dataSubscriptionResponse = webClientBuilder.build().post()
                .uri(credentials.getBaseUrl()+"/api/pay")
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(dataSubscriptionRequest)
                .retrieve()
                .bodyToMono(DataSubscriptionResponse.class)
                .block();
        if(dataSubscriptionResponse.code() == "000"){
            TransactionRequest transactionRequest = mapper.apply(dataSubscriptionRequest, dataSubscriptionResponse);
            return transactionService.saveTransaction(transactionRequest);
        }else{
            throw new TransactionException(dataSubscriptionResponse.response_description(), dataSubscriptionResponse.code(), dataSubscriptionRequest.getRequest_id());
        }
    }
}
