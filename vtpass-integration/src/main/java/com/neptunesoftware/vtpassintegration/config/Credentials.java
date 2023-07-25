package com.neptunesoftware.vtpassintegration.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "credentials", ignoreUnknownFields = false)
public class Credentials {
    private String apiKey;
    private String publicKey;
    private String secretKey;
    private String phoneNumber;

}
