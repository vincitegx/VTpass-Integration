package com.neptunesoftware.vtpassintegration.commons.controller;

import com.neptunesoftware.vtpassintegration.commons.response.ServiceCategoryResponse;
import com.neptunesoftware.vtpassintegration.commons.response.VariationCodeResponse;
import com.neptunesoftware.vtpassintegration.commons.service.ServiceCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/service-categories")
@RequiredArgsConstructor
public class ServiceCategoryController {

    private final ServiceCategoryService serviceCategoryService;
    @GetMapping
    public ResponseEntity<ServiceCategoryResponse> fetchServiceCategoryService() {
        return ResponseEntity.ok(serviceCategoryService.getServiceCategories());
    }
}
