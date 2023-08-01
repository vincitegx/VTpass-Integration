package com.neptunesoftware.vtpassintegration.electricity.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.electricity.exception.ElectricBillPaymentException;
import com.neptunesoftware.vtpassintegration.electricity.mapper.ElectricityMapper;
import com.neptunesoftware.vtpassintegration.electricity.request.ElectricBillRequest;
import com.neptunesoftware.vtpassintegration.electricity.request.VerifyMeterNumberRequest;
import com.neptunesoftware.vtpassintegration.electricity.response.*;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
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
        private final ElectricityMapper responseMapper;
    private final TransactionService transactionService;
    private final Credentials credentials;

    private VerifyMeterNumberResponse verifyMeterNumber(VerifyMeterNumberRequest request) {
        return webClient.build().post()
                .uri("https://sandbox.vtpass.com/api/merchant-verify")
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(VerifyMeterNumberResponse.class)
                .block();
    }

    public TransactionResponse electricityPayment(ElectricBillRequest request) {
        ElectricPostpaidResponse postpaidResponse;
        ElectricPrepaidResponse prepaidResponse;
        TransactionResponse response = null;

        ElectricBillResponseApi electricBillResponse;
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
            electricBillResponse = mapToPostPaidResponse(postpaidResponse);
            log.info("PostPaid Response: {}",electricBillResponse);
            if(!Objects.equals(electricBillResponse.getElectricPostpaidResponse().getCode(), "000"))  {
                throw new ElectricBillPaymentException("");
            }
            response = transactionService.saveTransaction(responseMapper.mapperToDB(
                    responseMapper.mapToPostPaidResponse(request,electricBillResponse)));
            log.info(response);
        }
        if (Objects.equals(request.getVariation_code(), "prepaid")) {
            prepaidResponse = deserializeAPIResponsePrepaid(jsonString);
            log.info("Prepaid Response: {}",prepaidResponse);
            if (!Objects.equals(prepaidResponse.getCode(), "000")) {
                throw new ElectricBillPaymentException("");
            }
            electricBillResponse = mapToPrePaidResponse(prepaidResponse);
            log.info(electricBillResponse);
            response = transactionService.saveTransaction(responseMapper.mapperToDB(
                    responseMapper.mapToPrepaidResponse(request,electricBillResponse)));
            log.info(response);
        }
        return response;
    }

    private static ElectricPrepaidResponse deserializeAPIResponsePrepaid(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(jsonString, ElectricPrepaidResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ElectricPrepaidResponse();
    }

    private static ElectricPostpaidResponse deserializeAPIResponsePostPaid(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(jsonString, ElectricPostpaidResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ElectricPostpaidResponse();
    }

    private ElectricBillResponseApi mapToPrePaidResponse(ElectricPrepaidResponse response) {
        return  ElectricBillResponseApi.builder()
                .electricPrepaidResponse(response).build();
    }

    private ElectricBillResponseApi mapToPostPaidResponse(ElectricPostpaidResponse response) {
        return  ElectricBillResponseApi.builder()
                .electricPostpaidResponse(response).build();
    }
}
