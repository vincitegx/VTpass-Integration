package com.neptunesoftware.vtpassintegration.commons.service;

import com.neptunesoftware.vtpassintegration.commons.response.ExtraFieldsResponse;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExtraFieldsService {
    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;
    public ExtraFieldsResponse getExtraFields(String serviceID) {
        try {
            log.info("Fetching extra fields...");
            return webClientBuilder.build().get()
                    .uri(credentials.getBaseUrl() + "/api/extra-fields",
                            uriBuilder -> uriBuilder.queryParam("serviceID", serviceID)
                                    .build())
                    .header("api-key", credentials.getApiKey())
                    .header("public-key", credentials.getPublicKey())
                    .retrieve()
                    .bodyToMono(ExtraFieldsResponse.class)
                    .block();
        } catch (Exception ex) {
            throw new TransactionException("ERROR, EXTRA FIELDS DOES NOT EXIST", "012", null);
        }
    }
}
