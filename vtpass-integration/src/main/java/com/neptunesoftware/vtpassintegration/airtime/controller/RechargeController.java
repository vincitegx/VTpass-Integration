package com.neptunesoftware.vtpassintegration.airtime.controller;

import com.neptunesoftware.vtpassintegration.airtime.request.AirtimeRequest;
import com.neptunesoftware.vtpassintegration.airtime.request.PurchaseIntlProductsRequest;
import com.neptunesoftware.vtpassintegration.airtime.response.IntlAirtimeOperatorsResponse;
import com.neptunesoftware.vtpassintegration.airtime.response.IntlCountriesResponse;
import com.neptunesoftware.vtpassintegration.airtime.response.IntlProductTypesResponse;
import com.neptunesoftware.vtpassintegration.airtime.service.RechargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/recharge")
@RequiredArgsConstructor
public class RechargeController {
    private final RechargeService rechargeService ;
    @PostMapping
    public ResponseEntity<?> rechargeAirtime(@Validated @RequestBody AirtimeRequest airtimeRequest){
        return ResponseEntity.ok(rechargeService.buyAirtime(airtimeRequest));
    }


    @GetMapping("intlcountires")
    //task : create a whole service class for intl calls ?????????????
    public ResponseEntity<IntlCountriesResponse> getIntlAirtimeCountries(){
        return ResponseEntity.ok(rechargeService.getIntlAirtimeCountries());
    }


    //GET International Airtime Product Types
    @GetMapping("intlairtimeproducts")
    //task : create a whole service class for intl calls ?????????????
    public ResponseEntity<IntlProductTypesResponse> getIntlAirtimeProducts(){
        return ResponseEntity.ok(rechargeService.getIntlAirtimeProducts());
    }


    //GET International Airtime Operators
    @GetMapping("intlairtimeoperators")
    //task : create a whole service class for intl calls ?????????????
    public ResponseEntity<IntlAirtimeOperatorsResponse> getIntlAirtimeOperators(){
        return ResponseEntity.ok(rechargeService.getIntlAirtimeOperators());
    }

    //GET International Varation Codes


    @PostMapping("purchaseintlproduct")
    public ResponseEntity<?> purchaseIntlProduct(@Validated @RequestBody PurchaseIntlProductsRequest purchaseIntlProductsRequest){
        return ResponseEntity.ok(rechargeService.purchaseIntlProduct(purchaseIntlProductsRequest));
    }

}
