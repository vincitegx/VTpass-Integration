package com.neptunesoftware.vtpassintegration.commons.controller;

import com.neptunesoftware.vtpassintegration.commons.response.ServiceIdResponse;
import com.neptunesoftware.vtpassintegration.commons.service.ServiceIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/services")
@RequiredArgsConstructor
public class ServiceIdController {
    private final ServiceIdService serviceIdService;
    @GetMapping
    public ResponseEntity<ServiceIdResponse> fetchServiceId(@RequestParam String identifier ) {
        return ResponseEntity.ok(serviceIdService.getServiceId(identifier));
    }
}
