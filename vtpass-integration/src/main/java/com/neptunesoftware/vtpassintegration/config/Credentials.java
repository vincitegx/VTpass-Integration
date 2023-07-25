package com.neptunesoftware.vtpassintegration.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "credentials", ignoreUnknownFields = false)
public class Credentials {
    private String apiKey;
    private String publicKey;
    private String secretKey;
    private String phoneNumber;
    private String baseUrl;
    private String channelName;
    private String paymentCurrency;
}
