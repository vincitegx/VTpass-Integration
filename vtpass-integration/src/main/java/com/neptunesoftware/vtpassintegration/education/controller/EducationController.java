package com.neptunesoftware.vtpassintegration.education.controller;

import com.neptunesoftware.vtpassintegration.education.request.JAMBProductPurchaseRequest;
import com.neptunesoftware.vtpassintegration.education.request.JAMBProfileVerificationRequest;
import com.neptunesoftware.vtpassintegration.education.request.ProductRegRequest;
import com.neptunesoftware.vtpassintegration.education.response.JAMBProfileVerificationResponse;
import com.neptunesoftware.vtpassintegration.education.service.JAMBPINVendingService;
import com.neptunesoftware.vtpassintegration.education.service.WAECRegistrationService;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/education")
@RequiredArgsConstructor
public class EducationController {

    private final WAECRegistrationService waecRegistrationService;
    private final JAMBPINVendingService jambPinVendingService;

    @PostMapping("waec")
    public ResponseEntity<TransactionResponse> purchaseWAECRegistration(@RequestBody ProductRegRequest request) {
        return ResponseEntity.ok(waecRegistrationService.purchaseWAECRegistration(request));
    }

    @PostMapping("waec-result")
    public ResponseEntity<TransactionResponse> purchaseWAECResult(@RequestBody ProductRegRequest request) {
        return ResponseEntity.ok(waecRegistrationService.purchaseWAECRegistration(request));
    }

    @PostMapping("verify")
    public ResponseEntity<JAMBProfileVerificationResponse> verifyJAMBProfile(@RequestBody JAMBProfileVerificationRequest request) {
        JAMBProfileVerificationResponse verificationResponse = jambPinVendingService.verifyJAMBProfile(request);
        return ResponseEntity.ok(verificationResponse);
    }

    @PostMapping("jamb")
    public ResponseEntity<TransactionResponse> purchaseJAMBProduct(@RequestBody JAMBProductPurchaseRequest request) {
        return ResponseEntity.ok(jambPinVendingService.purchaseJAMBProduct(request));
    }
}
