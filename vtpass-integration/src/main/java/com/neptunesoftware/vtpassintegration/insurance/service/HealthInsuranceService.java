package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.mapper.HealthInsuranceMapper;
import com.neptunesoftware.vtpassintegration.insurance.request.HealthInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceExtraFieldsResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceOptionsResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceVariationResponse;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    public HealthInsuranceExtraFieldsResponse getExtraFields() {
        String serviceId = "health-insurance-rhl";
        String apiUrl = "https://sandbox.vtpass.com/api/extra-fields?serviceID=" + serviceId;

        return webClientBuilder.build().get()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .retrieve()
                .bodyToMono(HealthInsuranceExtraFieldsResponse.class)
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

    public Integer purchaseHealthInsurance(HealthInsuranceRequest request) {
        request.setRequestId(requestIdGenerator.apply(4));
        String serviceId = "health-insurance-rhl";
        String apiUrl = "https://sandbox.vtpass.com/api/pay";

        HealthInsuranceResponse healthInsuranceResponse = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(HealthInsuranceResponse.class)
                .block();

        TransactionRequest transactionRequest = mapper.mapRequest(request, healthInsuranceResponse);
        Integer transactionResponse = service.saveTransaction(transactionRequest);

        return transactionResponse;
    }

    public HealthInsuranceResponse queryTransactionStatus(String requestId) {
        String apiUrl = "https://sandbox.vtpass.com/api/requery";

        Mono<HealthInsuranceResponse> responseMono = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue("request_id=" + requestId)
                .retrieve()
                .bodyToMono(HealthInsuranceResponse.class);

        return responseMono.block();
    }


}





