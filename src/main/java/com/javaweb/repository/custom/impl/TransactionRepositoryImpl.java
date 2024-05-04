package com.javaweb.repository.custom.impl;

import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.custom.TransactionRepositoryCustom;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepositoryImpl implements TransactionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TransactionEntity> findByCodeAndCustomerid(String code, Long customerid) {
        String sql = "select * FROM transaction as t where t.code = '" + code + "' " + "AND t.customerid = " + customerid;
        Query query = entityManager.createNativeQuery(sql, TransactionEntity.class);
        return (List<TransactionEntity>) query.getResultList();
    }

}
