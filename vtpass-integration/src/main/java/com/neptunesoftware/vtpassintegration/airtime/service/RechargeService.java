package com.neptunesoftware.vtpassintegration.airtime.service;


import com.neptunesoftware.vtpassintegration.airtime.mapper.AirtimeRechargeResponseMapper;
import com.neptunesoftware.vtpassintegration.airtime.request.AirtimeRequest;
import com.neptunesoftware.vtpassintegration.airtime.request.PurchaseIntlProductsRequest;
import com.neptunesoftware.vtpassintegration.airtime.response.*;
import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionQueryResponse;
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


    public Integer buyAirtime(AirtimeRequest airtimeRequest){
       airtimeRequest.setRequest_id(requestIdGenerator.apply(4));
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
        int transactionResponse = transactionService.saveTransaction(transactionRequest);
       return transactionResponse;
    }


    public IntlCountriesResponse getIntlAirtimeCountries(){


        return webClientBuilder.build().get()
                .uri("https://sandbox.vtpass.com/api/get-international-airtime-countries")
                .header("api-key",credentials.getApiKey())
                .header("secret-key",credentials.getSecretKey())
                .retrieve()
                .bodyToMono(IntlCountriesResponse.class)
                .block();
    }


    public IntlProductTypesResponse getIntlAirtimeProducts(){
        return webClientBuilder.build().get()
                .uri("https://sandbox.vtpass.com/api/get-international-airtime-product-types?code=GH")
                .header("api-key",credentials.getApiKey())
                .header("secret-key",credentials.getSecretKey())
                .retrieve()
                .bodyToMono(IntlProductTypesResponse.class)
                .block();


    }

    //GET International Airtime Operators
    public IntlAirtimeOperatorsResponse getIntlAirtimeOperators(){
        return webClientBuilder.build().get()
                .uri("https://sandbox.vtpass.com/api/get-international-airtime-operators?code=GH&product_type_id=4")
                .header("api-key",credentials.getApiKey())
                .header("secret-key",credentials.getSecretKey())
                .retrieve()
                .bodyToMono(IntlAirtimeOperatorsResponse.class)
                .block();


    }

    public int purchaseIntlProduct(PurchaseIntlProductsRequest purchaseIntlProductsRequest){
        purchaseIntlProductsRequest.setRequest_id(requestIdGenerator.apply(7));
            PurchaseIntlProductsResponse purchaseIntlProductsResponse = webClientBuilder.build().post()
                .uri("https://sandbox.vtpass.com/api/pay")
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(purchaseIntlProductsRequest)
                .retrieve()
                .bodyToMono(PurchaseIntlProductsResponse.class)
                .block();
        TransactionRequest transactionRequest = airtimeRechargeResponseMapper.applyMap(purchaseIntlProductsResponse,purchaseIntlProductsRequest);
        int transactionResponse = transactionService.saveTransaction(transactionRequest);
        return transactionResponse;
    }

    //GET Variation Codes

}

