package com.neptunesoftware.vtpassintegration.tv.controller;

import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.tv.response.TvSubscriptionResponseApi;
import com.neptunesoftware.vtpassintegration.tv.service.TvServices;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionStatusRequest;
import com.neptunesoftware.vtpassintegration.tv.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tv")
@Log4j2
public class TvController {
    private final TvServices tvServices;

 @GetMapping("/tvVariations")
    public ResponseEntity<TvVariationResponseApi> tvVariations(@RequestParam("serviceID") String serviceId) {
        return ResponseEntity.status(HttpStatus.OK).body(tvServices.tvVariations(serviceId));
    }

    @PostMapping("/tv-subscription")
    public ResponseEntity<TransactionResponse> tvSubscription(@RequestBody TvSubscriptionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tvServices.tvSubscription(request));
    }

    @PostMapping("/subscription-status")
    public ResponseEntity<TvSubscriptionStatusResponse> tvSubscriptionStatus(@RequestBody TvSubscriptionStatusRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tvServices.tvSubscriptionStatus(request));
    }
}
