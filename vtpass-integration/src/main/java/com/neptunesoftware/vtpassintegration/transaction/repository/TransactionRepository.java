package com.neptunesoftware.vtpassintegration.transaction.repository;

import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String INSERT_TRANSACTION_QUERY = """
            INSERT INTO ALT_VTPAY(REQUEST_ID,SERVICE_ID,TRAN_ID,TRAN_APPL,TRAN_SENDER,TRAN_AMOUNT,TRAN_RECEIVER,TRAN_STATUS,
            TRAN_METHOD, NARRATION, TRAN_PURPOSE, CHANNEL_NAME, ISREVERSAL,TRAN_DATE, TRAN_TYPE, PAYMENT_CURR, CHARGE_AMOUNT,TAX_AMOUNT,CODE) 
            VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
            """;
    private final String UPDATE_TRANSACTION_QUERY = """
            UPDATE ALT_VTPAY SET TRAN_STATUS = ?, TRAN_METHOD = ?, ISREVERSAL = ?, CODE = ? WHERE REQUEST_ID = ? AND TRAN_ID = ?
            """;
    public int saveTransaction(TransactionRequest transactionRequest){
        try {
            return jdbcTemplate.update(INSERT_TRANSACTION_QUERY,transactionRequest.getRequestId(),
                    transactionRequest.getServiceId(), transactionRequest.getTranId(), transactionRequest.getTranAppl(), transactionRequest.getTranSender(), transactionRequest.getTranAmount(),
                    transactionRequest.getTranReceiver(),transactionRequest.getTranStatus(), transactionRequest.getTranMethod(), transactionRequest.getNarration(),
                    transactionRequest.getTranPurpose(), transactionRequest.getChannelName(), transactionRequest.getIsReversal(), transactionRequest.getTranDate(),
                    transactionRequest.getTranType(), transactionRequest.getPaymentCurr(), transactionRequest.getChargeAmount(), transactionRequest.getTaxAmount(),transactionRequest.getCode());
        } catch (DataAccessException e) {
            log.error("Error saving transaction history to the database");
            log.error(e.getMessage());
            throw new TransactionException("Error saving transaction history to the database", null, transactionRequest.getRequestId());
        }
    }

    public int updateTransaction(TransactionRequest transactionRequest){
        try {
            return jdbcTemplate.update(UPDATE_TRANSACTION_QUERY,
                    transactionRequest.getTranStatus(), transactionRequest.getTranMethod(),
                    transactionRequest.getIsReversal(), transactionRequest.getRequestId(),
                    transactionRequest.getTranId(), transactionRequest.getCode());
        } catch (DataAccessException e) {
            log.error("Error updating transaction in the database");
            log.error(e.getMessage());
            throw new TransactionException("Error updating transaction in the database",null,transactionRequest.getRequestId());
        }
    }
}
