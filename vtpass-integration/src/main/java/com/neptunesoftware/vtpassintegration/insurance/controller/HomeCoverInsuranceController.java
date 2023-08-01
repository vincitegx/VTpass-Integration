package com.neptunesoftware.vtpassintegration.insurance.controller;

import com.neptunesoftware.vtpassintegration.insurance.domain.HomeCoverExtraField;
import com.neptunesoftware.vtpassintegration.insurance.domain.HomeCoverOption;
import com.neptunesoftware.vtpassintegration.insurance.request.HomeCoverPurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.service.HomeCoverInsuranceService;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home-cover-insurance")
@RequiredArgsConstructor
public class HomeCoverInsuranceController {

    private final HomeCoverInsuranceService homeCoverInsuranceService;

//    @GetMapping("/variation-codes")
//    public ResponseEntity<List<HomeCoverVariation>> getVariationCodes() {
//        List<HomeCoverVariation> variationCodes = homeCoverInsuranceService.getHomeCoverVariationCodes();
//        return ResponseEntity.ok(variationCodes);
//    }

    @GetMapping("/extra-fields")
    public ResponseEntity<List<HomeCoverExtraField>> getExtraFields() {
        List<HomeCoverExtraField> extraFields = homeCoverInsuranceService.getHomeCoverExtraFields();
        return ResponseEntity.ok(extraFields);
    }

    @GetMapping("/options")
    public ResponseEntity<?> getOptions(@RequestParam String name) {
        return ResponseEntity.ok(homeCoverInsuranceService.getHomeCoverOptions(name));
    }

    @PostMapping("/purchase")
    public ResponseEntity<TransactionResponse> purchaseInsurance(@RequestBody HomeCoverPurchaseRequest request) {
        TransactionResponse transactionResponse = homeCoverInsuranceService.purchaseHomeCoverInsurance(request);
        return ResponseEntity.ok(transactionResponse);
    }

//    @PostMapping("/query-transaction")
//    public ResponseEntity<HomeCoverTransactionStatus> queryTransactionStatus(@RequestParam String requestId) {
//        HomeCoverTransactionStatus status = homeCoverInsuranceService.queryTransactionStatus(requestId);
//        return ResponseEntity.ok(status);
//    }
}

