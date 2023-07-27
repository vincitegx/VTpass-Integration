package com.neptunesoftware.vtpassintegration.commons.response;

import java.util.List;

public record ServiceIdResponse(
        String response_description,
        List<ContentItem> content
) {
    public record ContentItem(
            String serviceID,
            String name,
            String minimum_amount,
            int maximum_amount,
            String convenience_fee,
            String product_type,
            String image
    ) {
    }
}
