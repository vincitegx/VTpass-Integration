package com.neptunesoftware.vtpassintegration.commons.service;

import com.neptunesoftware.vtpassintegration.commons.response.ServiceCategoryResponse;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ServiceCategoryService {
    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;

    public ServiceCategoryResponse getServiceCategories(){
        return webClientBuilder.build().get()
                .uri("https://sandbox.vtpass.com/api/service-categories")
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .retrieve()
                .bodyToMono(ServiceCategoryResponse.class)
                .block();
    }
}
