package com.neptunesoftware.vtpassintegration.commons.response;

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
            Options options
    ) {
    }

    public record Options(
            String adult,
            String child,
            String infant
    ) {
    }
}
