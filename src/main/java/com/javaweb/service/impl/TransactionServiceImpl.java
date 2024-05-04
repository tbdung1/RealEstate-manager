package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ITransactionService;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionConverter transactionConverter;
    @Override
    public List<TransactionDTO> transactionTypeCustomer(String transactionType, Long customerid) {
            List<TransactionEntity> transactionEntities = transactionRepository.findByCodeAndCustomerid(transactionType, customerid);
            List<TransactionDTO> transactionDTOList = new ArrayList<>();
            for(TransactionEntity x : transactionEntities){
                TransactionDTO transactionDTO = transactionConverter.convertToDTO(x);
                transactionDTOList.add(transactionDTO);
            }
        return transactionDTOList;
    }
@Override
public void addOrUpdateTransaction(TransactionDTO transactionDTO) {
    TransactionEntity transactionEntity = new TransactionEntity();
    List<TransactionEntity> transactionEntities = new ArrayList<>();
    if(transactionDTO.getId() != null){
        transactionEntity = transactionRepository.findById(transactionDTO.getId()).get();
        transactionDTO.setCode(transactionEntity.getCode());
        transactionDTO.setCreatedBy(transactionEntity.getCreatedBy());
        transactionDTO.setCreatedDate(transactionEntity.getCreatedDate());
    }
    transactionEntity = transactionConverter.convertToEntity(transactionDTO);
    transactionRepository.save(transactionEntity);
}

    @Override
    public void deleteTransaction(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).get();
        transactionRepository.delete(transactionEntity);
    }
}
