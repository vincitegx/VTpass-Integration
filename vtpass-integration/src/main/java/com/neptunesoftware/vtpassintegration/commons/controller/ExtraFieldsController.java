package com.neptunesoftware.vtpassintegration.commons.controller;

import com.neptunesoftware.vtpassintegration.commons.response.ExtraFieldsResponse;
import com.neptunesoftware.vtpassintegration.commons.service.ExtraFieldsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/extra-fields")
@RequiredArgsConstructor
public class ExtraFieldsController {
    private final ExtraFieldsService extraFieldsService;
    @GetMapping
    public ResponseEntity<ExtraFieldsResponse> getExtraFields(@RequestParam String serviceID) {
        return ResponseEntity.ok(extraFieldsService.getExtraFields(serviceID));
    }
}
