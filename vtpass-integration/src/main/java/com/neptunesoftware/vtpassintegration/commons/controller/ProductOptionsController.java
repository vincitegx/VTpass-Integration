package com.neptunesoftware.vtpassintegration.commons.controller;

import com.neptunesoftware.vtpassintegration.commons.response.ProductOptionsResponse;
import com.neptunesoftware.vtpassintegration.commons.service.ProductOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/options")
@RequiredArgsConstructor
public class ProductOptionsController {
    private final ProductOptionsService productOptionsService;
    @GetMapping
    public ResponseEntity<ProductOptionsResponse> fetchProductOptions(@RequestParam String serviceID, @RequestParam String name) {
        return ResponseEntity.ok(productOptionsService.getProductOptionsForAeroService(serviceID, name));
    }

}
