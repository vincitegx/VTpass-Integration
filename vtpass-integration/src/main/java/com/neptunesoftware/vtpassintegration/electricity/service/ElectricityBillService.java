package com.neptunesoftware.vtpassintegration.electricity.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.electricity.exception.ElectricBillPaymentException;
import com.neptunesoftware.vtpassintegration.electricity.request.ElectricBillRequest;
import com.neptunesoftware.vtpassintegration.electricity.request.VerifyMeterNumberRequest;
import com.neptunesoftware.vtpassintegration.electricity.response.ElectricBillGenericResponse;
import com.neptunesoftware.vtpassintegration.electricity.response.ElectricPostpaidResponse;
import com.neptunesoftware.vtpassintegration.electricity.response.ElectricPrepaidResponse;
import com.neptunesoftware.vtpassintegration.electricity.response.VerifyMeterNumberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class ElectricityBillService {
    private final WebClient.Builder webClient;
    private final RequestIdGenerator requestIdGenerator;
//    private final ElectricityMapperToDb responseMapper;
//    private final TransactionService transactionService;
    private final Credentials credentials;

    private VerifyMeterNumberResponse verifyMeterNumber(VerifyMeterNumberRequest request) {
         return webClient.build().post()
                .uri("https://sandbox.vtpass.com/api/merchant-verify")
                .header("api-key",credentials.getApiKey())
                .header("secret-key",credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(VerifyMeterNumberResponse.class)
                .block();
    }

    public ElectricBillGenericResponse<?> electricityPayment(ElectricBillRequest request) {
        ElectricPostpaidResponse postpaidResponse;
        ElectricPrepaidResponse prepaidResponse;
        VerifyMeterNumberResponse verifyMeterNumberResponse = verifyMeterNumber
                (new VerifyMeterNumberRequest(request.getBillersCode(),
                        request.getServiceID(), request.getVariation_code()));
        if (verifyMeterNumberResponse.getContent().getMeterNumber() == null) {
            throw new ElectricBillPaymentException("Invalid Meter number");
        }
        request.setRequest_id(requestIdGenerator.apply(4));
        String jsonString = webClient.build().post()
                    .uri("https://sandbox.vtpass.com/api/pay")
                    .header("api-key", credentials.getApiKey())
                    .header("secret-key", credentials.getSecretKey())
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            if (Objects.equals(request.getVariation_code(), "postpaid")) {
                postpaidResponse = deserializeAPIResponsePostPaid(jsonString);
                log.info(postpaidResponse);
//                assert postpaidResponse != null;
//                return new PostpaidResponse(postpaidResponse.getContent().getTransactions().getStatus(),postpaidResponse);
            }
            if (Objects.equals(request.getVariation_code(), "prepaid")) {
                prepaidResponse = deserializeAPIResponsePrepaid(jsonString);
                log.info(prepaidResponse);
//                assert prepaidResponse != null;
//                return new PrepaidResponse(prepaidResponse.getContent().getTransactions().getStatus(),prepaidResponse);

            }
              return null;
    }

    private static ElectricPrepaidResponse deserializeAPIResponsePrepaid(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, ElectricPrepaidResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static ElectricPostpaidResponse deserializeAPIResponsePostPaid(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, ElectricPostpaidResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
