package com.neptunesoftware.vtpassintegration.commons.service;

import com.neptunesoftware.vtpassintegration.commons.response.VariationCodeResponse;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class VariationCodeService {

    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;

    public VariationCodeResponse getVariationCode(String serviceID){
        try{
            return webClientBuilder.build().get()
                    .uri(credentials.getBaseUrl()+"/api/service-variations",
                            uriBuilder -> uriBuilder.queryParam("serviceID", serviceID).build())
                    .header("api-key", credentials.getApiKey())
                    .header("public-key", credentials.getPublicKey())
                    .retrieve()
                    .bodyToMono(VariationCodeResponse.class)
                    .block();
        }catch (Exception ex){
            throw new TransactionException("VARIATION CODE DOES NOT EXIST", "010", null);
        }
    }

    public VariationCodeResponse getVariationCodeAirtimeIntl(String serviceID, String operatorId, String productTypeId) {
        try{
            return webClientBuilder.build().get()
                    .uri(credentials.getBaseUrl()+"/api/service-variations",
                            uriBuilder -> uriBuilder.queryParam("serviceID", serviceID)
                                    .queryParam("operator_id", operatorId)
                                    .queryParam("product_type_id", productTypeId).build())
                    .header("api-key", credentials.getApiKey())
                    .header("public-key", credentials.getPublicKey())
                    .retrieve()
                    .bodyToMono(VariationCodeResponse.class)
                    .block();
        }catch (Exception ex){
            throw new TransactionException("VARIATION CODE DOES NOT EXIST", "010", null);
        }
    }
}
