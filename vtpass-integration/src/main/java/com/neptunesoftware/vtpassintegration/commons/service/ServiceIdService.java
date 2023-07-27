package com.neptunesoftware.vtpassintegration.commons.service;

import com.neptunesoftware.vtpassintegration.commons.response.ServiceIdResponse;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ServiceIdService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;

    public ServiceIdResponse getServiceId(String identifier){
        return webClientBuilder.build().get()
                .uri("https://sandbox.vtpass.com/api/services",
                        uriBuilder -> uriBuilder.queryParam("identifier", identifier).build())
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .retrieve()
                .bodyToMono(ServiceIdResponse.class)
                .block();
    }
}
