package com.neptunesoftware.vtpassintegration.airtime.response;

import java.util.List;

public record IntlAirtimeOperatorsResponse (
        String  response_description ,
        List <Content> content )
{

    public record Content(
            String operator_id ,
            String name ,
            String operator_image
    ){}
}
