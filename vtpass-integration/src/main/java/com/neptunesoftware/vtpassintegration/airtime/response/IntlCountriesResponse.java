package com.neptunesoftware.vtpassintegration.airtime.response;

import lombok.Builder;

import java.util.List;
@Builder
public record IntlCountriesResponse (
        String response_description ,
        Content content

) {


    public record Content(
         List<Countries> countries) {

    }

    public record Countries(
            String code,
            String flag,
            String name,
            String currency,
            String prefix
    ) {
    }

}