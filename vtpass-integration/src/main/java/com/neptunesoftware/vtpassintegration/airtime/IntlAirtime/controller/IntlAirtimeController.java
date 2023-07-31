//package com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.controller;
//
//import com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.response.IntlAirtimeOperatorsResponse;
//import com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.response.IntlCountriesResponse;
//import com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.response.IntlProductTypesResponse;
//import com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.response.IntlVariationCodes;
//import com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.service.IntlAirtimeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("api/v1/recharge")
//@RequiredArgsConstructor
//public class IntlAirtimeController {
//
//    private final IntlAirtimeService intlAirtimeService ;
//
//
//    //GET International Airtime Countries
////    @GetMapping
////    //task : create a whole service class for intl calls ?????????????
////    public ResponseEntity<IntlCountriesResponse> getIntlAirtimeCountries(){
////        return ResponseEntity.ok(intlAirtimeService.getIntlAirtimeCountries());
////    }
////
////
////    //GET International Airtime Product Types
////    @GetMapping
////    //task : create a whole service class for intl calls ?????????????
////    public ResponseEntity<IntlProductTypesResponse> getIntlAirtimeProducts(){
////        return ResponseEntity.ok(intlAirtimeService.getIntlAirtimeProducts());
////    }
////
////
////    //GET International Airtime Operators
////    @GetMapping
////    //task : create a whole service class for intl calls ?????????????
////    public ResponseEntity<IntlAirtimeOperatorsResponse> getIntlAirtimeOperators(){
////        return ResponseEntity.ok(intlAirtimeService.getIntlAirtimeOperators());
////    }
////
////
////    //GET International Airtime Operators
////    @GetMapping
////    //task : create a whole service class for intl calls ?????????????
////    public ResponseEntity<IntlVariationCodes> getIntlVariationCodes(@RequestParam String serviceID){
////        return ResponseEntity.ok(intlAirtimeService.getIntlVariationCodes(serviceID));
////    }
//}
