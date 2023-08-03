package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.mapper.PersonalAccidentInsuranceMapper;
import com.neptunesoftware.vtpassintegration.insurance.request.PersonalAccidentInsurancePurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.PersonalAccidentInsuranceResponse;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class PersonalAccidentInsuranceService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    private final RequestIdGenerator requestIdGenerator;
    private final TransactionService service;
    private final PersonalAccidentInsuranceMapper mapper;

    public TransactionResponse purchasePersonalAccidentInsurance(PersonalAccidentInsurancePurchaseRequest request) {
        String apiUrl = credentials.getBaseUrl()+"/api/pay";

        request.setRequest_id(requestIdGenerator.apply(4));

        PersonalAccidentInsuranceResponse response = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PersonalAccidentInsuranceResponse.class)
                .block();
        System.out.println(response);

        if (response.getCode().equals("000")){
            TransactionRequest transactionRequest = mapper.mapper(request, response);
            return service.saveTransaction(transactionRequest);
        }else {
            throw new TransactionException(response.getResponseDescription(), response.getCode(), response.getRequestId());
        }

    }




}

