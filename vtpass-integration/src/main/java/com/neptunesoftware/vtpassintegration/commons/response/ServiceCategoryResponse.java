package com.neptunesoftware.vtpassintegration.commons.response;

import java.util.List;

public record ServiceCategoryResponse(
        String response_description,
        List<ContentItem> content
) {
    public record ContentItem(
            String identifier,
            String name
    ){}
}
