package com.neptunesoftware.vtpassintegration.education.controller;

import com.neptunesoftware.vtpassintegration.education.request.ProductRegRequest;
import com.neptunesoftware.vtpassintegration.education.service.WAECRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/waec-registration")
@RequiredArgsConstructor
public class WAECRegistrationController {

    private WAECRegistrationService waecRegistrationService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseWAECRegistration(@RequestBody ProductRegRequest request) {
        return ResponseEntity.ok(waecRegistrationService.purchaseWAECRegistration(request));
    }
}
