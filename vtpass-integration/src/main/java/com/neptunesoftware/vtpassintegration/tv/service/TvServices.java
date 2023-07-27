package com.neptunesoftware.vtpassintegration.tv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import com.neptunesoftware.vtpassintegration.tv.mapper.TvSubscriptionMapperResponse;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionStatusRequest;
import com.neptunesoftware.vtpassintegration.tv.request.VerifySmartCardNumberRequest;
import com.neptunesoftware.vtpassintegration.tv.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TvServices {
private final WebClient.Builder webClient;
    private final RequestIdGenerator requestIdGenerator;
    private final TvSubscriptionMapperResponse responseMapper;
    private final TransactionService transactionService;

    private static final String VT_PASS_BASE_URL = "https://sandbox.vtpass.com/api";
    public SmartCardResponse verifySmartCardNumber(VerifySmartCardNumberRequest request) {
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
        bodyValues.add("billersCode", request.getBillersCode());
        bodyValues.add("serviceID", request.getServiceID());

      SmartCardVerificationApiResponse response = webClient.build().post()
                .uri(VT_PASS_BASE_URL+"/merchant-verify") 
                .header("api-key","0a20145c6e0706bf1afd3d4765db8905")
                .header("secret-key","SK_21598ae28ff25e053e011b9ea6a5088565d870b5c55")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(bodyValues))
                .retrieve()
                .bodyToMono(SmartCardVerificationApiResponse.class)
                .block();
        assert response != null;
        log.info("Customer name: {}",response.getContent().getCustomerName());
        log.info("Bouquet: {}", response.getContent().getCurrentBouquet());
        log.info("The amount due for renewing the current bouquet: {}", "N"+response.getContent().getRenewalAmount());
        SmartCardResponse vtPassResponse = new SmartCardResponse();
        vtPassResponse.setCardHolderName(response.getContent().getCustomerName());
        vtPassResponse.setCurrentBouquet(response.getContent().getCurrentBouquet());
        vtPassResponse.setAmountDueRenewal(String.valueOf(response.getContent().getRenewalAmount()));
        vtPassResponse.setStatus(response.getContent().getStatus());
        vtPassResponse.setCurrentBouquetCode(response.getContent().getCurrentBouquetCode());
        vtPassResponse.setDueDate(response.getContent().getDueDate());

        return vtPassResponse;
    }

    public TvSubscriptionStatusResponse tvSubscription(TvSubscriptionRequest  request) {

        VerifySmartCardNumberRequest smartCardNumberRequest = new VerifySmartCardNumberRequest();
        smartCardNumberRequest.setServiceID(request.getServiceID());
        smartCardNumberRequest.setBillersCode(request.getBillersCode());

        //Response from verify-smart-card
        SmartCardResponse smartCardVerification = verifySmartCardNumber(smartCardNumberRequest);

        //Payload for making the API call
        MultiValueMap<String, String> requestDataForTvSubscription = new LinkedMultiValueMap<>();
        requestDataForTvSubscription.add("request_id",requestIdGenerator.apply(4) );
        requestDataForTvSubscription.add("serviceID", request.getServiceID());
        requestDataForTvSubscription.add("billersCode",request.getBillersCode());
        requestDataForTvSubscription.add("variation_code", smartCardVerification.getCurrentBouquetCode());
        requestDataForTvSubscription.add("amount", String.valueOf(request.getAmount()));
        requestDataForTvSubscription.add("phone", request.getPhone());
        requestDataForTvSubscription.add("subscription_type", request.getSubscription_type());
        requestDataForTvSubscription.add("quantity",request.getQuantity());

        TvSubscriptionStatusResponse response = webClient.build().post()
                .uri(VT_PASS_BASE_URL+"/pay") // Replace with your actual URL
                .header("api-key","0a20145c6e0706bf1afd3d4765db8905")
                .header("secret-key","SK_21598ae28ff25e053e011b9ea6a5088565d870b5c55")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(requestDataForTvSubscription))
                .retrieve()
                .bodyToMono(TvSubscriptionStatusResponse.class)
                .block();
        assert response != null;
          log.info("Response TvSubscription message {}",response.getResponseDescription());
        TransactionRequest transactionRequest = responseMapper.mapper(request,response);

        int dbResponse =  transactionService.saveTransaction(transactionRequest);
        if (dbResponse > 0) {
            log.info("********* SUBSCRIPTION SUCCESSFUL**********");
            log.info("********* TRANSACTION SAVED TO DATABASE SUCCESSFULLY**********");
        }
        return response;
    }
    
    public TvSubscriptionResponse tvSubscriptionStatus(TvSubscriptionStatusRequest request) {
        MultiValueMap<String, String> queryPayload = new LinkedMultiValueMap<>();
        queryPayload.add("request_id", request.getRequest_id() );
        TvSubscriptionStatusResponse response = webClient.build().post()
                .uri(VT_PASS_BASE_URL+"/requery") // Replace with your actual URL
                .header("api-key","0a20145c6e0706bf1afd3d4765db8905")
                .header("secret-key","SK_21598ae28ff25e053e011b9ea6a5088565d870b5c55")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(queryPayload))
                .retrieve()
                .bodyToMono(TvSubscriptionStatusResponse.class)
                .block();
        assert response != null;
        log.info("Response TvSubscription message {}",response.getResponseDescription());

        return TvSubscriptionResponse.builder()
                .status(response.getResponseDescription())
                .request_id(response.getRequestId()).build();
    }

    public TvVariationResponse tvVariations(String serviceID) {

        String jsonString = webClient.baseUrl(VT_PASS_BASE_URL+"/service-variations").build()
                .get().uri( uriBuilder -> uriBuilder.queryParam("serviceID", serviceID).build())
                .header("api-key", "0a20145c6e0706bf1afd3d4765db8905")
                .header("public-key", "PK_923798cdb4d63ee6856c613a2eacd5bf6fff2ae2509")
                .retrieve()
                .bodyToMono(String.class)
                .block();
             TvVariationFromApi responseFromApi = deserializeAPIResponse(jsonString);
             log.info(responseFromApi);
        assert jsonString != null;
        log.info("Response: {}", jsonString);
        assert responseFromApi != null;
        return mapTvVariationFromApiToResponse(responseFromApi);
    }

    private static TvVariationFromApi deserializeAPIResponse(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, TvVariationFromApi.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static TvVariationResponse mapTvVariationFromApiToResponse(TvVariationFromApi tvVariationFromApi) {
        return TvVariationResponse.builder()
                .serviceName(tvVariationFromApi.getContent().getServiceName())
                .convenienceFee(tvVariationFromApi.getContent().getConvinience_fee())
                .variations(tvVariationFromApi.getContent().getVarations())
                .build();
    }
}
