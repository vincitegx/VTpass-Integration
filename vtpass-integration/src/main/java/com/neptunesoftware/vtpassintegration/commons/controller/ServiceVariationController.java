package com.neptunesoftware.vtpassintegration.commons.controller;

import com.neptunesoftware.vtpassintegration.commons.response.ServiceIdResponse;
import com.neptunesoftware.vtpassintegration.commons.response.VariationCodeResponse;
import com.neptunesoftware.vtpassintegration.commons.service.VariationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/service-variations")
@RequiredArgsConstructor
public class ServiceVariationController {
    private final VariationCodeService variationCodeService;
    @GetMapping
    public ResponseEntity<VariationCodeResponse> fetchVariationCode(@RequestParam String serviceID ) {
        return ResponseEntity.ok(variationCodeService.getServiceId(serviceID));
    }
}
