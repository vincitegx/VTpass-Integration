package com.neptunesoftware.vtpassintegration.transaction.mapper;

import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionResponseMapper implements RowMapper<TransactionResponse> {
    @Override
    public TransactionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TransactionResponse();
    }
}
