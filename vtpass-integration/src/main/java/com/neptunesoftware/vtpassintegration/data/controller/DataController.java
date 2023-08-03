package com.neptunesoftware.vtpassintegration.data.controller;

import com.neptunesoftware.vtpassintegration.data.request.DataSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.data.request.SmileVerificationRequest;
import com.neptunesoftware.vtpassintegration.data.response.SmileVerificationResponse;
import com.neptunesoftware.vtpassintegration.data.service.DataSubscriptionService;
import com.neptunesoftware.vtpassintegration.data.service.SmileVerificationService;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final DataSubscriptionService dataSubscriptionService;
    private final SmileVerificationService smileVerificationService;
    @PostMapping
    public ResponseEntity<TransactionResponse> handleDataSubscription(@RequestBody DataSubscriptionRequest dataSubscriptionRequest) {
        return ResponseEntity.ok(dataSubscriptionService.subscribeForData(dataSubscriptionRequest));
    }

    @PostMapping("smile/verify-email")
    public ResponseEntity<SmileVerificationResponse> verifySmileEmail(@RequestBody SmileVerificationRequest smileVerificationRequest) {
        return ResponseEntity.ok(smileVerificationService.verifySmileEmail(smileVerificationRequest));
    }
}
