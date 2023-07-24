package com.neptunesoftware.vtpassintegration.data.controller;

import com.neptunesoftware.vtpassintegration.data.request.DataSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.data.service.DataSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private DataSubscriptionService dataSubscriptionService;
    @PostMapping
    public ResponseEntity<?> handleDataSubscription(@Validated @RequestBody DataSubscriptionRequest dataSubscriptionRequest) {
        return ResponseEntity.ok(dataSubscriptionService.subscribeForData(dataSubscriptionRequest));
    }

}
