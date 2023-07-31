package com.neptunesoftware.vtpassintegration.insurance.response;

import com.neptunesoftware.vtpassintegration.insurance.domain.HomeCoverExtraField;
import lombok.Data;

import java.util.List;

@Data
public class HomeCoverExtraFieldResponse {

    private String response_description;
    private String ServiceName;
    private String serviceID;
    private List<HomeCoverExtraField> content;
}
