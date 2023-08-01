package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.domain.HomeCoverExtraField;
import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceContent;
import com.neptunesoftware.vtpassintegration.insurance.mapper.HomeCoverInsuranceResponseMapper;
import com.neptunesoftware.vtpassintegration.insurance.request.HomeCoverPurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.HomeCoverExtraFieldResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.HomeCoverOptionResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.HomeCoverPurchaseResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeCoverInsuranceService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService transactionService;
    private final HomeCoverInsuranceResponseMapper responseMapper;
    private final RequestIdGenerator requestIdGenerator;

    // Method to get variation codes for Home Cover Insurance plans
//    public List<HomeCoverVariation> getHomeCoverVariationCodes() {
//        String apiUrl = "https://sandbox.vtpass.com/api/service-variations?serviceID=home-cover-insurance";
//
//        // Perform the HTTP GET request to the VTpass API
//        HomeCoverVariationResponse variationResponse = webClientBuilder.build().get()
//                .uri(apiUrl)
//                .header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader())
//                .retrieve()
//                .bodyToMono(HomeCoverVariationResponse.class)
//                .block();
//
//        if (variationResponse != null && "000".equals(variationResponse.getResponse_description())) {
//            return variationResponse.getContent().getVarations();
//        } else {
//            throw new RuntimeException("Failed to fetch Home Cover Insurance variation codes");
//        }
//    }

    // Method to get extra fields for Home Cover Insurance plans
    public List<HomeCoverExtraField> getHomeCoverExtraFields() {
        String apiUrl = "https://sandbox.vtpass.com/api/extra-fields?serviceID=home-cover-insurance";

        // Perform the HTTP GET request to the VTpass API
        HomeCoverExtraFieldResponse extraFieldResponse = webClientBuilder.build().get()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .retrieve()
                .bodyToMono(HomeCoverExtraFieldResponse.class)
                .block();

        if (extraFieldResponse != null && "000".equals(extraFieldResponse.getResponse_description())) {
            return extraFieldResponse.getContent();
        } else {
            throw new RuntimeException("Failed to fetch Home Cover Insurance extra fields");
        }
    }

    // Method to get options for a specific extra field in Home Cover Insurance plans
    public InsuranceContent getHomeCoverOptions(String optionName) {
        String apiUrl = "https://sandbox.vtpass.com/api/options?serviceID=home-cover-insurance&name=" + optionName;

        // Perform the HTTP GET request to the VTpass API
        HomeCoverOptionResponse optionResponse = webClientBuilder.build().get()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .retrieve()
                .bodyToMono(HomeCoverOptionResponse.class)
                .block();

        if (optionResponse != null && "000".equals(optionResponse.getResponse_description())) {
            return optionResponse.getContent();
        } else {
            throw new RuntimeException("Failed to fetch options for Home Cover Insurance field: " + optionName);
        }
    }

    // Method to purchase Home Cover Insurance
    public TransactionResponse purchaseHomeCoverInsurance(HomeCoverPurchaseRequest request) {
        String apiUrl = "https://sandbox.vtpass.com/api/pay";
        request.setRequest_id(requestIdGenerator.apply(4));
        request.setServiceID("home-cover-insurance");

        // Perform the HTTP POST request to the VTpass API
        HomeCoverPurchaseResponse purchaseResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(HomeCoverPurchaseResponse.class)
                .block();

        if (purchaseResponse != null && "000".equals(purchaseResponse.getCode())) {
            TransactionRequest transactionRequest = responseMapper.mapRequest(request, purchaseResponse);
            TransactionResponse transactionResponse = transactionService.saveTransaction(transactionRequest);
            return transactionResponse;
        } else {
            throw new RuntimeException("Failed to purchase Home Cover Insurance");
        }
    }


}

