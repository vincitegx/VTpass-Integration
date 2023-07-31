package com.neptunesoftware.vtpassintegration.insurance.controller;

import com.neptunesoftware.vtpassintegration.insurance.request.ThirdPartyInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.ThirdPartyInsuranceResponse;
import com.neptunesoftware.vtpassintegration.insurance.service.ThirdPartyInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/third-party-insurance")
public class ThirdPartyInsuranceController {

    private final ThirdPartyInsuranceService insuranceService;


//    @GetMapping("/get-variation-codes")
//    public ThirdPartyInsuranceResponse getVariationCodes() {
//        return insuranceService.getVariationCodes();
//    }

    @PostMapping("/purchase-product")
    public ResponseEntity<?> purchaseProduct(@RequestBody ThirdPartyInsuranceRequest request) {
        return ResponseEntity.ok(insuranceService.purchaseProduct(request));
    }

//    @PostMapping("/query-transaction-status")
//    public ThirdPartyInsuranceResponse queryTransactionStatus(@RequestParam("request_id") String requestId) {
//        return insuranceService.queryTransactionStatus(requestId);
//    }
}

