package com.javaweb.repository.custom;

import com.javaweb.entity.TransactionEntity;

import java.util.List;

public interface TransactionRepositoryCustom {
    public List<TransactionEntity> findByCodeAndCustomerid(String code, Long customerid);
}
