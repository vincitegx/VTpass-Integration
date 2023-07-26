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
        MultiValueMap<String, String> airtimePayload = new LinkedMultiValueMap<>();
        airtimePayload.add("request_id", requestIdGenerator.apply(4));
        airtimePayload.add("serviceID", airtimeRequest.getServiceID());
        airtimePayload.add("amount",airtimeRequest.getAmount().toString());
        airtimePayload.add("phone", airtimeRequest.getPhone());
        //airtimeRequest.setRequest_id(requestIdGenerator.apply(4));

        AirtimeResponse airtimeResponse = webClientBuilder.build().post()
                .uri("https://sandbox.vtpass.com/api/pay")
                .header("api-key","0cbaed4fcee1f9ab06344119b70cfd8c")
                .header("secret-key","SK_420b2619ddf6a8c0c6e1c6556f391ced33901a31d0d")
                .bodyValue(airtimePayload)
                .retrieve()
                .bodyToMono(AirtimeResponse.class)
                .block();
        log.info("Response: {}",airtimeResponse.getResponse_description());
     TransactionRequest transactionRequest = airtimeRechargeResponseMapper.apply(airtimeResponse,airtimeRequest);
       log.info("Mapper: {}",transactionRequest);
//        int transactionResponse = transactionService.saveTransaction(transactionRequest);
//        return transactionResponse;
       return airtimeResponse;

    }
}
