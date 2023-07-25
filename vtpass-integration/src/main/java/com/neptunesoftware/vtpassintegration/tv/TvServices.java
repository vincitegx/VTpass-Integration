package com.neptunesoftware.vtpassintegration.tv;

import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionStatusRequest;
import com.neptunesoftware.vtpassintegration.tv.request.VerifySmartCardNumberRequest;
import com.neptunesoftware.vtpassintegration.tv.response.TvSubscriptionStatusResponse;
import com.neptunesoftware.vtpassintegration.tv.response.VerifySmartCardNumberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Log4j2
public class TvServices {
    private final WebClient webClient = WebClient.create();
    private static final String VT_PASS_BASE_URL = "https://sandbox.vtpass.com/api/merchant-verify";
    public VerifySmartCardNumberResponse verifySmartCardNumber(VerifySmartCardNumberRequest request) {
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
        bodyValues.add("billersCode", request.getBillersCode());
        bodyValues.add("serviceID", request.getServiceID());

        VerifySmartCardNumberResponse response = webClient.post()
                .uri(VT_PASS_BASE_URL) // Replace with your actual URL
                .header("api-key","0cbaed4fcee1f9ab06344119b70cfd8c")
                .header("secret-key","SK_420b2619ddf6a8c0c6e1c6556f391ced33901a31d0d")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(bodyValues))
                .retrieve()
                .bodyToMono(VerifySmartCardNumberResponse.class)
                .block();
        log.info("Customer name: {}",response.getContent().getCustomerName());
        log.info("Bouquet {}", response.getContent().getCurrentBouquet());

        return response;
    }
    
    public TvSubscriptionStatusResponse tvSubscriptionStatus(TvSubscriptionStatusRequest request) {
        return null;
    }
}
