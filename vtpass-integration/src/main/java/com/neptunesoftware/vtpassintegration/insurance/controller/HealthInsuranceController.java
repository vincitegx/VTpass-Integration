package com.neptunesoftware.vtpassintegration.insurance.controller;

import com.neptunesoftware.vtpassintegration.insurance.request.HealthInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.InsuranceExtraFieldsResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.HealthInsuranceOptionsResponse;
import com.neptunesoftware.vtpassintegration.insurance.service.HealthInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/health-insurance")
public class HealthInsuranceController {

    private final HealthInsuranceService insuranceService;

    @Autowired
    public HealthInsuranceController(HealthInsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping("get-extra-fields")
    public InsuranceExtraFieldsResponse getExtraFields() {
        return insuranceService.getExtraFields();
    }

    @GetMapping("get-options")
    public HealthInsuranceOptionsResponse getOptions(@RequestParam("name") String name) {
        return insuranceService.getOptions(name);
    }

    @PostMapping("product")
    public ResponseEntity<?> purchaseProduct(@RequestBody HealthInsuranceRequest request) {
        return ResponseEntity.ok(insuranceService.purchaseHealthInsurance(request));
    }

}

