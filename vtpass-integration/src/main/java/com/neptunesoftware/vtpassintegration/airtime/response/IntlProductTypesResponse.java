package com.neptunesoftware.vtpassintegration.airtime.response;
import java.util.List;

public record IntlProductTypesResponse (
        String  response_description,
        List <Content> content
){

    public record Content(
            String product_type_id ,
            String name
    ){}
}

