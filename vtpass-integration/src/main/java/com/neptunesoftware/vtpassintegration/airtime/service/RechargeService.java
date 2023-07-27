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
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Log4j2
@Service
public class RechargeService {

    private final WebClient.Builder webClientBuilder ;
    private final Credentials credentials ;
    private final  TransactionService transactionService ;
    private final AirtimeRechargeResponseMapper airtimeRechargeResponseMapper ;

    private final RequestIdGenerator requestIdGenerator;


    public AirtimeResponse buyAirtime(AirtimeRequest airtimeRequest){
       airtimeRequest.setRequest_id(requestIdGenerator.apply(4));
//        MultiValueMap<String, String> airtimePayload = new LinkedMultiValueMap<>();
//        airtimePayload.add("request_id", requestIdGenerator.apply(4));
//        airtimePayload.add("serviceID", airtimeRequest.getServiceID());
//        airtimePayload.add("amount",airtimeRequest.getAmount().toString());
//        airtimePayload.add("phone", airtimeRequest.getPhone());
        //airtimeRequest.setRequest_id(requestIdGenerator.apply(4));

        AirtimeResponse airtimeResponse = webClientBuilder.build().post()
                .uri("https://sandbox.vtpass.com/api/pay")
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(airtimeRequest)
                .retrieve()
                .bodyToMono(AirtimeResponse.class)
                .block();
        log.info("Response: {}",airtimeResponse.getResponse_description());
        log.info("TransactionId: {}",airtimeResponse.getTransactionId());
     TransactionRequest transactionRequest = airtimeRechargeResponseMapper.apply(airtimeResponse,airtimeRequest);
       log.info("Mapper: {}",transactionRequest);

//        int transactionResponse = transactionService.saveTransaction(transactionRequest);
//        return transactionResponse;
       return airtimeResponse;

    }
    }

