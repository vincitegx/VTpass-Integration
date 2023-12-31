package com.neptunesoftware.vtpassintegration.commons.service;

import com.neptunesoftware.vtpassintegration.commons.response.ProductOptionsResponse;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ProductOptionsService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;

    public ProductOptionsResponse getProductOptionsForAeroService(String serviceID, String name){
        try{
            return webClientBuilder.build().get()
                    .uri(credentials.getBaseUrl()+"/api/options",
                            uriBuilder -> uriBuilder
                                    .queryParam("serviceID", serviceID)
                                    .queryParam("name", name).build())
                    .header("api-key", credentials.getApiKey())
                    .header("secret-key", credentials.getSecretKey())
                    .retrieve()
                    .bodyToMono(ProductOptionsResponse.class)
                    .block();
        }catch (Exception ex){
            throw new TransactionException("PRODUCT DOES NOT EXIST", "012", null);
        }

    }
}
