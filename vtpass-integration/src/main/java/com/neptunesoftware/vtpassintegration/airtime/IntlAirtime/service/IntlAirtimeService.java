//package com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.service;
//
//import com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.response.IntlAirtimeOperatorsResponse;
//import com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.response.IntlCountriesResponse;
//import com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.response.IntlProductTypesResponse;
//import com.neptunesoftware.vtpassintegration.airtime.IntlAirtime.response.IntlVariationCodes;
//import com.neptunesoftware.vtpassintegration.config.Credentials;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Service
//@AllArgsConstructor
//  public class IntlAirtimeService {
//   private final Credentials credentials;
//   private final WebClient.Builder webClientBuilder;
//
//
//
//    //GET International Airtime Countries
//    public IntlCountriesResponse getIntlAirtimeCountries(){
//        return webClientBuilder.build().get()
//                .uri(" https://sandbox.vtpass.com/api/get-international-airtime-countries")
//                .header("api-key",credentials.getApiKey())
//                .header("secret-key",credentials.getSecretKey())
//                .retrieve()
//                .bodyToMono(IntlCountriesResponse.class)
//                .block();
//    }
//
//
//    //GET International Airtime Product Types
//    public IntlProductTypesResponse getIntlAirtimeProducts(){
//        return webClientBuilder.build().get()
//                .uri("https://sandbox.vtpass.com/api/get-international-airtime-product-types?code=GH")
//                .header("api-key",credentials.getApiKey())
//                .header("secret-key",credentials.getSecretKey())
//                .retrieve()
//                .bodyToMono(IntlProductTypesResponse.class)
//                .block();
//
//
//    }
//
//
//    //GET International Airtime Operators
//    public IntlAirtimeOperatorsResponse getIntlAirtimeOperators(){
//        return webClientBuilder.build().get()
//                .uri("https://sandbox.vtpass.com/api/get-international-airtime-operators?code=GH&product_type_id=4")
//                .header("api-key",credentials.getApiKey())
//                .header("secret-key",credentials.getSecretKey())
//                .retrieve()
//                .bodyToMono(IntlAirtimeOperatorsResponse.class)
//                .block();
//
//
//    }
//
//
//
//    //GET Variation Codes
//    public IntlVariationCodes getIntlVariationCodes(String serviceID){
//        return webClientBuilder.build().get()
//                .uri(" https://sandbox.vtpass.com/api/service-variations?serviceID=foreign-airtime&operator_id=1&product_type_id=1",
//                        uriBuilder -> uriBuilder.queryParam("serviceID", serviceID).build())
//                .header("api-key", credentials.getApiKey())
//                .header("secret-key", credentials.getSecretKey())
//                .retrieve()
//                .bodyToMono(IntlVariationCodes.class)
//                .block();
//    }
//
//}
