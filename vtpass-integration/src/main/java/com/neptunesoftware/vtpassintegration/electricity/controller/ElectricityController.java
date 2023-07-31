package com.neptunesoftware.vtpassintegration.electricity.controller;

import com.neptunesoftware.vtpassintegration.electricity.request.ElectricBillRequest;
import com.neptunesoftware.vtpassintegration.electricity.response.ElectricBillGenericResponse;
import com.neptunesoftware.vtpassintegration.electricity.service.ElectricityBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/electricity")
@Log4j2
//@Tag(name = "electricityPayment")
public class ElectricityController {
    private final ElectricityBillService electricityService;

//        @PostMapping("/verify-meter-number")
//        public ResponseEntity<VerifyMeterNumberResponse> verifyMeterNumber(@RequestBody VerifyMeterNumberRequest request) {
//            return ResponseEntity.status(HttpStatus.OK).body(electricityService.verifyMeterNumber(request));
//        }
        @PostMapping("/electricity-payment")
        public ResponseEntity<ElectricBillGenericResponse<?>> electricityPayment(@RequestBody ElectricBillRequest request) {
            return ResponseEntity.status(HttpStatus.OK).body(electricityService.electricityPayment(request));
        }
}