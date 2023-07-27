package com.neptunesoftware.vtpassintegration.education.request;


import lombok.Data;

@Data
public class ProductRegRequest {
    private String request_id;
    private String serviceID;
    private String variation_code;
    private Double amount;
    private Integer quantity;
    private String phone;

}
