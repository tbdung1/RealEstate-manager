package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;
import java.util.Map;

public interface ITransactionService {
    public List<TransactionDTO> transactionTypeCustomer(String transactionType, Long customerid);
    public void addOrUpdateTransaction(TransactionDTO transactionDTO);
    public void deleteTransaction(Long id);
    public String loadTransactionDetail(Long id);
}
