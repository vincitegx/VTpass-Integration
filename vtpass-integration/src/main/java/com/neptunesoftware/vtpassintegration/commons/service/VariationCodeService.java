package com.neptunesoftware.vtpassintegration.commons.service;

import com.neptunesoftware.vtpassintegration.commons.response.ServiceIdResponse;
import com.neptunesoftware.vtpassintegration.commons.response.VariationCodeResponse;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class VariationCodeService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;

    public VariationCodeResponse getServiceId(String serviceID){
        return webClientBuilder.build().get()
                .uri("https://sandbox.vtpass.com/api/service-variations",
                        uriBuilder -> uriBuilder.queryParam("serviceID", serviceID).build())
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .retrieve()
                .bodyToMono(VariationCodeResponse.class)
                .block();
    }
}
