package com.neptunesoftware.vtpassintegration.airtime.service;

import com.neptunesoftware.vtpassintegration.airtime.mapper.AirtimeRechargeResponseMapper;
import com.neptunesoftware.vtpassintegration.airtime.request.AirtimeRequest;
import com.neptunesoftware.vtpassintegration.airtime.request.PurchaseIntlProductsRequest;
import com.neptunesoftware.vtpassintegration.airtime.response.*;
import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
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


    public TransactionResponse buyAirtime(AirtimeRequest airtimeRequest){
       airtimeRequest.setRequest_id(requestIdGenerator.apply(4));
        AirtimeResponse airtimeResponse = webClientBuilder.build().post()
                .uri(credentials.getBaseUrl()+"/api/pay")
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(airtimeRequest)
                .retrieve()
                .bodyToMono(AirtimeResponse.class)
                .block();
        log.info("Response: {}",airtimeResponse.getResponse_description());
        log.info("TransactionId: {}",airtimeResponse.getTransactionId());
        if(airtimeResponse.getCode().equals("000")){
            TransactionRequest transactionRequest = airtimeRechargeResponseMapper.apply(airtimeResponse,airtimeRequest);
            return transactionService.saveTransaction(transactionRequest);
        }else{
            throw new TransactionException(airtimeResponse.getResponse_description(), airtimeResponse.getCode(), airtimeResponse.getRequestId());
        }
    }


    public IntlCountriesResponse getIntlAirtimeCountries(){
        try {
           return webClientBuilder.build().get()
                    .uri(credentials.getBaseUrl()+"/api/get-international-airtime-countries")
                    .header("api-key", credentials.getApiKey())
                    .header("public-key", credentials.getPublicKey())
                    .retrieve()
                    .bodyToMono(IntlCountriesResponse.class)
                    .block();
        }
         catch (Exception e){
            throw new TransactionException (e.getMessage(), null, null);
        }
    }


    public IntlProductTypesResponse getIntlAirtimeProducts(String code){
        try {
            return webClientBuilder.build().get()
                    .uri(credentials.getBaseUrl()+"/api/get-international-airtime-product-types",
                            uriBuilder -> uriBuilder
                            .queryParam("code", code).build())
                    .header("api-key", credentials.getApiKey())
                    .header("public-key", credentials.getPublicKey())
                    .retrieve()
                    .bodyToMono(IntlProductTypesResponse.class)
                    .block();
        }        catch (Exception e){
            throw new TransactionException (e.getMessage(), null, null);
        }
    }

    //GET International Airtime Operators
    public IntlAirtimeOperatorsResponse getIntlAirtimeOperators(String code, String product_type_id){
        try {
        return webClientBuilder.build().get()
                .uri(credentials.getBaseUrl()+"/api/get-international-airtime-operators",
                        uriBuilder -> uriBuilder
                                .queryParam("code", code)
                                .queryParam("product_type_id", product_type_id)
                                .build())
                .header("api-key",credentials.getApiKey())
                .header("public-key",credentials.getPublicKey())
                .retrieve()
                .bodyToMono(IntlAirtimeOperatorsResponse.class)
                .block();
        }
        catch (Exception e){
            throw new TransactionException (e.getMessage(), null, null);
        }
    }

    public TransactionResponse purchaseIntlProduct(PurchaseIntlProductsRequest purchaseIntlProductsRequest){

        purchaseIntlProductsRequest.setRequest_id(requestIdGenerator.apply(7));
            PurchaseIntlProductsResponse purchaseIntlProductsResponse = webClientBuilder.build().post()
                .uri(credentials.getBaseUrl()+"/api/pay")
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(purchaseIntlProductsRequest)
                .retrieve()
                .bodyToMono(PurchaseIntlProductsResponse.class)
                .block();
        if(purchaseIntlProductsResponse.code().equals("000")) {
                log.info(purchaseIntlProductsResponse);
                TransactionRequest transactionRequest = airtimeRechargeResponseMapper.applyMap(purchaseIntlProductsResponse, purchaseIntlProductsRequest);
                log.info(transactionRequest);
                return transactionService.saveTransaction(transactionRequest);
            }
            else {
                throw new TransactionException(purchaseIntlProductsResponse.response_description(),purchaseIntlProductsResponse.code(),purchaseIntlProductsResponse.requestId());
            }
    }

}

