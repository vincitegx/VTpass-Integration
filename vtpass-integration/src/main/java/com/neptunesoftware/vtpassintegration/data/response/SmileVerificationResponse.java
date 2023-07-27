package com.neptunesoftware.vtpassintegration.data.response;

import java.math.BigInteger;
import java.util.List;

public record SmileVerificationResponse(
        String code,
        Content content
) {
    public record Content(
            String Customer_Name,
            AccountList AccountList
    ){}
    public record AccountList(
            List<Account> Account,
            Integer NumberOfAccounts
    ){}
    public record Account(
            BigInteger AccountId,
            String FriendlyName
    ){}
}
