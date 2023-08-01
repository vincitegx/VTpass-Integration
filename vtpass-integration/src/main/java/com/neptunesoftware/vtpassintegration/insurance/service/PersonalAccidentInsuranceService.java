package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.insurance.mapper.PersonalAccidentInsuranceMapper;
import com.neptunesoftware.vtpassintegration.insurance.request.PersonalAccidentInsurancePurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.request.PersonalAccidentInsuranceQueryRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.PersonalAccidentInsuranceQueryResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.PersonalAccidentInsuranceResponse;
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
        String apiUrl = "https://sandbox.vtpass.com/api/pay";
        String serviceId = "personal-accident-insurance";

        request.setRequest_id(requestIdGenerator.apply(4));
        request.setServiceID(serviceId);

        PersonalAccidentInsuranceResponse response = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PersonalAccidentInsuranceResponse.class)
                .block();
        System.out.println(response);

        TransactionRequest transactionRequest = mapper.mapper(request, response);
        TransactionResponse transactionResponse = service.saveTransaction(transactionRequest);
        return transactionResponse;
    }

    public PersonalAccidentInsuranceQueryResponse queryTransactionStatus(String requestId) {
        String apiUrl = "https://api-service.vtpass.com/api/requery";

        PersonalAccidentInsuranceQueryResponse response = webClientBuilder.build().post()
                .uri(apiUrl)
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(new PersonalAccidentInsuranceQueryRequest())
                .retrieve()
                .bodyToMono(PersonalAccidentInsuranceQueryResponse.class)
                .block();

        return response;
    }


}

