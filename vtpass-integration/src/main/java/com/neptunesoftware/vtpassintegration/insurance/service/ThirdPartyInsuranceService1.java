package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.insurance.request.ThirdPartyInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.ThirdPartyInsuranceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ThirdPartyInsuranceService1 {

    @Value("${api.username}")
    private String username;

    @Value("${api.password}")
    private String password;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    public ThirdPartyInsuranceResponse getVariationCodes() {
        String endpoint = "https://sandbox.vtpass.com/api/service-variations?serviceID=ui-insure";
        headers.setBasicAuth(username, password);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ThirdPartyInsuranceResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, ThirdPartyInsuranceResponse.class);
        return response.getBody();
    }

    public ThirdPartyInsuranceResponse purchaseProduct(ThirdPartyInsuranceRequest request) {
        String endpoint = " https://sandbox.vtpass.com/api/pay";
        headers.setBasicAuth(username, password);
        HttpEntity<ThirdPartyInsuranceRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<ThirdPartyInsuranceResponse> response = restTemplate.exchange(endpoint, HttpMethod.POST, entity, ThirdPartyInsuranceResponse.class);
        return response.getBody();
    }

    public ThirdPartyInsuranceResponse queryTransactionStatus(String requestId) {
        String endpoint = " https://sandbox.vtpass.com/api/pay/requery";
        headers.setBasicAuth(username, password);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("request_id", requestId);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        ResponseEntity<ThirdPartyInsuranceResponse> response = restTemplate.exchange(endpoint, HttpMethod.POST, entity, ThirdPartyInsuranceResponse.class);
        return response.getBody();
    }
}

