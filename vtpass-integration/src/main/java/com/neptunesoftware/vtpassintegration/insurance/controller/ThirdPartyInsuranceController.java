package com.neptunesoftware.vtpassintegration.insurance.controller;

import com.neptunesoftware.vtpassintegration.insurance.request.ThirdPartyInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.ThirdPartyInsuranceResponse;
import com.neptunesoftware.vtpassintegration.insurance.service.ThirdPartyInsuranceService;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/third-party-insurance")
public class ThirdPartyInsuranceController {

    private final ThirdPartyInsuranceService insuranceService;

    @PostMapping("product")
    public ResponseEntity<TransactionResponse> purchaseProduct(@RequestBody ThirdPartyInsuranceRequest request) {
        return ResponseEntity.ok(insuranceService.purchaseProduct(request));
    }
}

