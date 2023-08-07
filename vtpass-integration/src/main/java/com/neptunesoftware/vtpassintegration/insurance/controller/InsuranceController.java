package com.neptunesoftware.vtpassintegration.insurance.controller;

import com.neptunesoftware.vtpassintegration.insurance.request.HealthInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.request.HomeCoverPurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.request.PersonalAccidentInsurancePurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.request.ThirdPartyInsuranceRequest;
import com.neptunesoftware.vtpassintegration.insurance.service.HealthInsuranceService;
import com.neptunesoftware.vtpassintegration.insurance.service.HomeCoverInsuranceService;
import com.neptunesoftware.vtpassintegration.insurance.service.PersonalAccidentInsuranceService;
import com.neptunesoftware.vtpassintegration.insurance.service.ThirdPartyInsuranceService;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final PersonalAccidentInsuranceService personalAccidentInsuranceService;
    private final HealthInsuranceService healthInsuranceService;
    private final HomeCoverInsuranceService homeCoverInsuranceService;
    private final ThirdPartyInsuranceService thirdPartyInsuranceService;

    @PostMapping("health")
    public ResponseEntity<TransactionResponse> purchaseHealthProduct(@RequestBody HealthInsuranceRequest request) {
        return ResponseEntity.ok(healthInsuranceService.purchaseHealthInsurance(request));
    }

    @PostMapping("personal")
    public ResponseEntity<TransactionResponse> purchasePersonalInsurance(@RequestBody PersonalAccidentInsurancePurchaseRequest request) {
        return  ResponseEntity.ok(personalAccidentInsuranceService.purchasePersonalAccidentInsurance(request));
    }

    @PostMapping("home-cover")
    public ResponseEntity<TransactionResponse> purchaseHomeCoverInsurance(@RequestBody HomeCoverPurchaseRequest request) {
        TransactionResponse transactionResponse = homeCoverInsuranceService.purchaseHomeCoverInsurance(request);
        return ResponseEntity.ok(transactionResponse);
    }

    @PostMapping("third-party")
    public ResponseEntity<TransactionResponse> purchaseMotorProduct(@RequestBody ThirdPartyInsuranceRequest request) {
        return ResponseEntity.ok(thirdPartyInsuranceService.purchaseProduct(request));
    }

}
