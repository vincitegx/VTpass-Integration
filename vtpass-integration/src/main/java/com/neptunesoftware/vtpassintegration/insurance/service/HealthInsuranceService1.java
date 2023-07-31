package com.neptunesoftware.vtpassintegration.insurance.service;

import com.neptunesoftware.vtpassintegration.insurance.request.HealthInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceOptionsResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HealthInsuranceService1 {

    @Value("${api.username}")
    private String username;

    @Value("${api.password}")
    private String password;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    public HealthInsuranceResponse getVariationCodes() {
        String endpoint = "https://sandbox.vtpass.com/api/service-variations?serviceID=health-insurance-rhl";
        headers.setBasicAuth(username, password);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<HealthInsuranceResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, HealthInsuranceResponse.class);
        return response.getBody();
    }

    public HealthInsuranceOptionsResponse getExtraFields() {
        String endpoint = "https://sandbox.vtpass.com/api/extra-fields?serviceID=health-insurance-rhl";
        headers.setBasicAuth(username, password);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<HealthInsuranceOptionsResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, HealthInsuranceOptionsResponse.class);
        return response.getBody();
    }

    public HealthInsuranceOptionsResponse getOptionsForField(String fieldName) {
        String endpoint = " https://sandbox.vtpass.com/api/options?serviceID=health-insurance-rhl&name=selected_hospital" + fieldName;
        headers.setBasicAuth(username, password);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<HealthInsuranceOptionsResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, HealthInsuranceOptionsResponse.class);
        return response.getBody();
    }

    public HealthInsuranceResponse purchaseProduct(HealthInsuranceRequest request) {
        String endpoint = "https://sandbox.vtpass.com/api/pay";
        headers.setBasicAuth(username, password);
        HttpEntity<HealthInsuranceRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<HealthInsuranceResponse> response = restTemplate.exchange(endpoint, HttpMethod.POST, entity, HealthInsuranceResponse.class);
        return response.getBody();

    }

}