package com.neptunesoftware.vtpassintegration.insurance.controller;

import com.neptunesoftware.vtpassintegration.insurance.request.PersonalAccidentInsurancePurchaseRequest;
import com.neptunesoftware.vtpassintegration.insurance.response.PersonalAccidentInsuranceQueryResponse;
import com.neptunesoftware.vtpassintegration.insurance.response.PersonalAccidentInsuranceResponse;
import com.neptunesoftware.vtpassintegration.insurance.service.PersonalAccidentInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/personal-accident-insurance")
public class PersonalAccidentInsuranceController {

    private final PersonalAccidentInsuranceService insuranceService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseInsurance(@RequestBody PersonalAccidentInsurancePurchaseRequest request) {
        return  ResponseEntity.ok(insuranceService.purchasePersonalAccidentInsurance(request));
    }


}

