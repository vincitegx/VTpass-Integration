package com.neptunesoftware.vtpassintegration.education.controller;

import com.neptunesoftware.vtpassintegration.education.request.JAMBProductPurchaseRequest;
import com.neptunesoftware.vtpassintegration.education.request.JAMBProfileVerificationRequest;
import com.neptunesoftware.vtpassintegration.education.response.JAMBProductPurchaseResponse;
import com.neptunesoftware.vtpassintegration.education.response.JAMBProfileVerificationResponse;
import com.neptunesoftware.vtpassintegration.education.service.JAMBPINVendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/jamb")
@RequiredArgsConstructor
public class JAMBPinVendingController {
    private final JAMBPINVendingService jambPinVendingService;

    @PostMapping("verify")
    public ResponseEntity<JAMBProfileVerificationResponse> verifyJAMBProfile(@RequestBody JAMBProfileVerificationRequest request) {
        JAMBProfileVerificationResponse verificationResponse = jambPinVendingService.verifyJAMBProfile(request);
        return ResponseEntity.ok(verificationResponse);
    }

    @PostMapping("purchase")
    public ResponseEntity<?> purchaseJAMBProduct(@RequestBody JAMBProductPurchaseRequest request) {
        return ResponseEntity.ok(jambPinVendingService.purchaseJAMBProduct(request));

    }

}

