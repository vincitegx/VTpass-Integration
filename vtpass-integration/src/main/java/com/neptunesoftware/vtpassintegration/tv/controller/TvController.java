package com.neptunesoftware.vtpassintegration.tv.controller;

import com.neptunesoftware.vtpassintegration.tv.service.TvServices;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionStatusRequest;
import com.neptunesoftware.vtpassintegration.tv.request.VerifySmartCardNumberRequest;
import com.neptunesoftware.vtpassintegration.tv.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/tv")
@Log4j2
//@Tag(name = "tvSubscription")
public class TvController {
    private final TvServices tvServices;
    @PostMapping("/smart-card-number-verification")
    public ResponseEntity<SmartCardResponse> verifySmartCardNumber(@RequestBody VerifySmartCardNumberRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tvServices.verifySmartCardNumber(request));
    }
 @GetMapping("/tvVariation")
    public ResponseEntity<TvVariationResponse> tvVariations(@RequestParam("serviceID") String serviceId) {
        return ResponseEntity.status(HttpStatus.OK).body(tvServices.tvVariations(serviceId));
    }

    @PostMapping("/tv-subscription")
    public ResponseEntity<TvSubscriptionStatusResponse> tvSubscription(@RequestBody TvSubscriptionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tvServices.tvSubscription(request));
    }

    @PostMapping("/subscription-status")
    public ResponseEntity<TvSubscriptionResponse> tvSubscriptionStatus(@RequestBody TvSubscriptionStatusRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tvServices.tvSubscriptionStatus(request));
    }
}
