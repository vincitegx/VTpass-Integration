package com.neptunesoftware.vtpassintegration.tv.controller;

import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.tv.service.TvServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tv")
public class TvController {
    private final TvServices tvServices;

    @PostMapping("subscription")
    public ResponseEntity<TransactionResponse> tvSubscription(@RequestBody TvSubscriptionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tvServices.tvSubscription(request));
    }
}