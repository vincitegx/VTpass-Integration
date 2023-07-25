package com.neptunesoftware.vtpassintegration.airtime.service;

import com.neptunesoftware.vtpassintegration.airtime.mapper.AirtimeRechargeResponseMapper;
import com.neptunesoftware.vtpassintegration.airtime.request.AirtimeRequest;
import com.neptunesoftware.vtpassintegration.airtime.response.AirtimeResponse;
import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.config.WebClientConfig;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class RechargeService {
    private final WebClient.Builder webClientBuilder ;
    private final Credentials credentials ;
    private final  TransactionService transactionService ;
    private final AirtimeRechargeResponseMapper airtimeRechargeResponseMapper ;

    private final RequestIdGenerator requestIdGenerator;


    public TransactionResponse buyAirtime(AirtimeRequest airtimeRequest){
        airtimeRequest.setServiceID(requestIdGenerator.apply(4));
        AirtimeResponse airtimeResponse = webClientBuilder.build().post()
                .uri("https://sandbox.vtpass.com/api/pay")
                .header("api-key",credentials.getApiKey())
                .header("secret-key",credentials.getSecretKey())
                .bodyValue(airtimeRequest)
                .retrieve()
                .bodyToMono(AirtimeResponse.class)
                .block();
        TransactionRequest transactionRequest = airtimeRechargeResponseMapper.apply(airtimeResponse);
        TransactionResponse transactionResponse = transactionService.saveTransaction(transactionRequest);
        return transactionResponse;

    }
}
