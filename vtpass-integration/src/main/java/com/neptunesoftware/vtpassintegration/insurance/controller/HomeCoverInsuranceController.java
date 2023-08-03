package com.neptunesoftware.vtpassintegration.insurance.controller;

import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceExtraField;
import com.neptunesoftware.vtpassintegration.insurance.request.HomeCoverPurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.service.HomeCoverInsuranceService;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/home-cover-insurance")
@RequiredArgsConstructor
public class HomeCoverInsuranceController {

    private final HomeCoverInsuranceService homeCoverInsuranceService;

    @GetMapping("extra-fields")
    public ResponseEntity<List<InsuranceExtraField>> getExtraFields() {
        List<InsuranceExtraField> extraFields = homeCoverInsuranceService.getHomeCoverExtraFields();
        return ResponseEntity.ok(extraFields);
    }

    @GetMapping("options")
    public ResponseEntity<?> getOptions(@RequestParam String name) {
        return ResponseEntity.ok(homeCoverInsuranceService.getHomeCoverOptions(name));
    }

    @PostMapping("purchase")
    public ResponseEntity<TransactionResponse> purchaseInsurance(@RequestBody HomeCoverPurchaseRequest request) {
        TransactionResponse transactionResponse = homeCoverInsuranceService.purchaseHomeCoverInsurance(request);
        return ResponseEntity.ok(transactionResponse);
    }
}

