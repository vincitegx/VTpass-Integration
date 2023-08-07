package com.neptunesoftware.vtpassintegration.commons.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record ExtraFieldsResponse(
        String response_description,
        String ServiceName,
        String serviceID,
        List<Content> content
) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Content(
            String optionName,
            String optionType,
            String optionLabel,
            String optionRule
    ){}
}
