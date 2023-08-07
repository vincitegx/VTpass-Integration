package com.neptunesoftware.vtpassintegration.transaction.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;

import com.neptunesoftware.vtpassintegration.education.domain.Card;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TransactionQueryResponse(
        String code,
                                       Content content,
                                       String response_description,
                                       String requestId,
                                       String amount,
                                       TransactionDate transaction_date,
                                       String purchased_code ,
                                       List<Card> cards ,
                                       List<String> tokens,

                                       String optionName,
                                       String optionType,
                                       String optionLabel,
                                       String optionRule,
                                       Map<String, String> options,
                                       String certUrl,
                                       String taxAmount,

                                       //lIBERTY
                                       String customerName,
                                       String customerAddress,
                                       String utilityName,
                                       String exchangeReference,
                                       String balance,
                                       Double tokenAmount,
                                       String resetToken,
                                       String configureToken,
                                       String units ,
                                       String fixChargeAmount,
                                       String tariff



) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Content(
            String Customer_Name,
            String serviceName,
            String serviceID,
            String optionName,
            String optionType,
            String optionLabel,
            String optionRule,
            Map<String, Map<String, String>> options,
            Transaction transactions
    ) {
    }
    public record Transaction(
            String status,
            String product_name,
            String unique_element,
            String unit_price,
            Integer quantity,
            String service_verification,
            String channel,
            Integer commission,
            String total_amount,
            Integer discount,
            String type,
            String email,
            String phone,
            String name,
            Integer convenience_fee,
            String amount,
            String platform,
            String method,

            String transactionId
    ) {
    }

    public record TransactionDate(
            String date,
            Integer timezone_type,
            String timezone
    ) {
    }
}
