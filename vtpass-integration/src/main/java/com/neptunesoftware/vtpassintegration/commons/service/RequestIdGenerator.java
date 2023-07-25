package com.neptunesoftware.vtpassintegration.commons.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.IntFunction;

@Component
public class RequestIdGenerator implements IntFunction<String> {
    @Override
    public String apply(int value) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Africa/Lagos"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String date = now.format(formatter);
        return date+RandomStringUtils.random(4, 0, 0, true, true, null, new SecureRandom());
    }
}