package com.neptunesoftware.vtpassintegration.airtime.controller;

import com.neptunesoftware.vtpassintegration.airtime.request.AirtimeRequest;
import com.neptunesoftware.vtpassintegration.airtime.request.PurchaseIntlProductsRequest;
import com.neptunesoftware.vtpassintegration.airtime.response.IntlAirtimeOperatorsResponse;
import com.neptunesoftware.vtpassintegration.airtime.response.IntlCountriesResponse;
import com.neptunesoftware.vtpassintegration.airtime.response.IntlProductTypesResponse;
import com.neptunesoftware.vtpassintegration.airtime.response.PurchaseIntlProductsResponse;
import com.neptunesoftware.vtpassintegration.airtime.service.RechargeService;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/recharge")
@RequiredArgsConstructor
public class RechargeController {
    private final RechargeService rechargeService ;

//Purchase products
    @PostMapping
    public ResponseEntity<TransactionResponse> rechargeAirtime(@RequestBody AirtimeRequest airtimeRequest){
        return ResponseEntity.ok(rechargeService.buyAirtime(airtimeRequest));
    }

// GET International Airtime Countries
    @GetMapping("countries/intl")
    public ResponseEntity<IntlCountriesResponse> getIntlAirtimeCountries(){
        return ResponseEntity.ok(rechargeService.getIntlAirtimeCountries());
    }


//GET International Airtime Product Types
    @GetMapping("products/intl")
    public ResponseEntity<IntlProductTypesResponse> getIntlAirtimeProducts(@RequestParam String code){
        return ResponseEntity.ok(rechargeService.getIntlAirtimeProducts(code));
    }


//GET International Airtime Operators
    @GetMapping("operators/intl")
    public ResponseEntity<IntlAirtimeOperatorsResponse> getIntlAirtimeOperators(@RequestParam String code, @RequestParam("product_type_id") String product_type_id){
        return ResponseEntity.ok(rechargeService.getIntlAirtimeOperators(code, product_type_id));
    }



//PURCHASE INTERNATIONAL PRODUCT
    @PostMapping("product/intl")
    public ResponseEntity<TransactionResponse> purchaseIntlProduct(@RequestBody PurchaseIntlProductsRequest purchaseIntlProductsRequest){
        return ResponseEntity.ok(rechargeService.purchaseIntlProduct(purchaseIntlProductsRequest));
    }

}
