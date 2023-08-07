package com.neptunesoftware.vtpassintegration.commons.service;

import com.neptunesoftware.vtpassintegration.commons.response.ServiceIdResponse;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ServiceIdService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;

    public ServiceIdResponse getServiceId(String identifier){
        try{
            return webClientBuilder.build().get()
                    .uri(credentials.getBaseUrl()+"/api/services",
                            uriBuilder -> uriBuilder.queryParam("identifier", identifier).build())
                    .header("api-key", credentials.getApiKey())
                    .header("public-key", credentials.getPublicKey())
                    .retrieve()
                    .bodyToMono(ServiceIdResponse.class)
                    .block();
        }catch (Exception ex){
            throw new TransactionException("Invalid Identifier", null, null);
        }
    }
}
