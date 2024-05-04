package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CustomerRepository customerRepository;
    public TransactionDTO convertToDTO(TransactionEntity transactionEntity){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        TransactionDTO result = modelMapper.map(transactionEntity, TransactionDTO.class);
        result.setTransactionDetail(transactionEntity.getNote());
        return result;
    }
    public TransactionEntity convertToEntity(TransactionDTO transactionDTO) {
        TransactionEntity result = modelMapper.map(transactionDTO, TransactionEntity.class);
        result.setNote(transactionDTO.getTransactionDetail());
        CustomerEntity customerEntity = customerRepository.findById(transactionDTO.getCustomerId()).get();
        result.setCustomerEntity(customerEntity);
        return result;
    }
}
