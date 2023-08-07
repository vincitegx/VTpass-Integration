package com.neptunesoftware.vtpassintegration.commons.response;

import java.util.Map;

public record ProductOptionsResponse(
        String response_description,
        Content content
) {
    public record Content(
            String ServiceName,
            String serviceID,
            String optionName,
            String optionType,
            String optionLabel,
            String optionRule,
            Map<String, Object> options
    ) {
    }
}
