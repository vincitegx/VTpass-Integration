package com.neptunesoftware.vtpassintegration.airtime.controller;

import com.neptunesoftware.vtpassintegration.airtime.request.AirtimeRequest;
import com.neptunesoftware.vtpassintegration.airtime.service.RechargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/recharge")
@RequiredArgsConstructor
public class RechargeController {

    private RechargeService rechargeService ;

    @PostMapping
    public ResponseEntity<?> rechargeAirtimr(@Validated @RequestBody AirtimeRequest airtimeRequest){
        return ResponseEntity.ok(rechargeService.buyAirtime(airtimeRequest));
    }





}
