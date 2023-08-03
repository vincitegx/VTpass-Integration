package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.mapper.HealthInsuranceMapper;
import com.neptunesoftware.vtpassintegration.insurance.request.HealthInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.InsuranceExtraFieldsResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceOptionsResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceVariationResponse;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class HealthInsuranceService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final TransactionService service;
    private final HealthInsuranceMapper mapper;
    private final RequestIdGenerator requestIdGenerator;

    public HealthInsuranceVariationResponse getVariationCodes() {
        String serviceId = "health-insurance-rhl";
        String apiUrl = "https://sandbox.vtpass.com/api/service-variations?serviceID=" + serviceId;

        return webClientBuilder.build().get()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .retrieve()
                .bodyToMono(HealthInsuranceVariationResponse.class)
                .block();
    }

    public InsuranceExtraFieldsResponse getExtraFields() {
        String serviceId = "health-insurance-rhl";
        String apiUrl = "https://sandbox.vtpass.com/api/extra-fields?serviceID=" + serviceId;

        return webClientBuilder.build().get()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .retrieve()
                .bodyToMono(InsuranceExtraFieldsResponse.class)
                .block();
    }

    public HealthInsuranceOptionsResponse getOptions(String name) {
        String serviceId = "health-insurance-rhl";
        String apiUrl = "https://sandbox.vtpass.com/api/options?serviceID=" + serviceId + "&name=" + name;

        return webClientBuilder.build().get()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .retrieve()
                .bodyToMono(HealthInsuranceOptionsResponse.class)
                .block();
    }

    public TransactionResponse purchaseHealthInsurance(HealthInsuranceRequest request) {
        request.setRequest_id(requestIdGenerator.apply(4));
        System.out.println(request);
        String apiUrl = credentials.getBaseUrl()+"/api/pay";

        HealthInsuranceResponse healthInsuranceResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(HealthInsuranceResponse.class)
                .block();
        System.out.println(healthInsuranceResponse);


        if (healthInsuranceResponse.getCode().equals("000")){
            TransactionRequest transactionRequest = mapper.mapRequest(request, healthInsuranceResponse);
            return service.saveTransaction(transactionRequest);
        }else {
            throw new TransactionException(healthInsuranceResponse.getResponseDescription(), healthInsuranceResponse.getCode(), healthInsuranceResponse.getRequestId());
        }

    }



}





