package com.neptunesoftware.vtpassintegration.insurance.controller;

import com.neptunesoftware.vtpassintegration.insurance.request.PersonalAccidentInsurancePurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.service.PersonalAccidentInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/personal-accident-insurance")
public class PersonalAccidentInsuranceController {

    private final PersonalAccidentInsuranceService insuranceService;

    @PostMapping("purchase")
    public ResponseEntity<?> purchaseInsurance(@RequestBody PersonalAccidentInsurancePurchaseRequest request) {
        return  ResponseEntity.ok(insuranceService.purchasePersonalAccidentInsurance(request));
    }


}

