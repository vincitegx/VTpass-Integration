package com.neptunesoftware.vtpassintegration.commons.response;

import java.util.List;

public record VariationCodeResponse(
        String response_description,
        Content content
) {
    public record Content(
            String ServiceName,
            String serviceID,
            String convinience_fee,
            List<Variations> varations
    ) {
    }

    public record Variations(
            String variation_code,
            String name,
            String variation_amount,
            String fixedPrice
    ) {
    }
}
