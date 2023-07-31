package com.neptunesoftware.vtpassintegration.data.service;

import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.data.request.SmileVerificationRequest;
import com.neptunesoftware.vtpassintegration.data.response.SmileVerificationResponse;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class SmileVerificationService {
    private final Credentials credentials;
    private final WebClient.Builder webClientBuilder;

    public SmileVerificationResponse verifySmileEmail(SmileVerificationRequest smileVerificationRequest){
        try{
            return webClientBuilder.build().post()
                    .uri("https://sandbox.vtpass.com/api/merchant-verify/smile/email")
                    .header("api-key", credentials.getApiKey())
                    .header("secret-key", credentials.getSecretKey())
                    .bodyValue(smileVerificationRequest)
                    .retrieve()
                    .bodyToMono(SmileVerificationResponse.class)
                    .block();
        }catch (Exception ex){
            throw new TransactionException("PRODUCT DOES NOT EXIST", "012", null);
        }
    }
}
