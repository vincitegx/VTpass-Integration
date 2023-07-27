package com.neptunesoftware.vtpassintegration.commons.controller;

import com.neptunesoftware.vtpassintegration.commons.response.AeroResponse;
import com.neptunesoftware.vtpassintegration.commons.response.ServiceIdResponse;
import com.neptunesoftware.vtpassintegration.commons.service.AeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/options")
@RequiredArgsConstructor
public class AeroController {
    private final AeroService aeroService;
    @GetMapping
    public ResponseEntity<AeroResponse> fetchProductOptions(@RequestParam String serviceID, @RequestParam String name) {
        return ResponseEntity.ok(aeroService.getProductOptionsForAeroService(serviceID, name));
    }

}
