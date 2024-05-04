package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public void setJoin(CustomerSearchRequest customerSearchRequest, StringBuilder sql) {
        // TODO Auto-generated method stub
        Long staffId = customerSearchRequest.getStaffId();
        if(staffId != null) {
            sql.append("INNER JOIN assignmentcustomer a\n"
                    + "ON a.customerid = c.id\n");
        }
    }
    public void queryNormal(CustomerSearchRequest customerSearchRequest, StringBuilder where){
        // TODO Auto-generated method stub
        try {
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId")) {
                    Object value = item.get(customerSearchRequest);
                    if(value != null && value != "") {
                        where.append("AND c." + fieldName + " LIKE '%" + value + "%' ");
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void querySpecial(CustomerSearchRequest customerSearchRequest, StringBuilder where) {
        Long staffId = customerSearchRequest.getStaffId();
        if (staffId != null) {
            where.append("AND a.staffid = " + staffId + " ");
        }
    }
    @Override
    public List<CustomerEntity> findAllCustomer(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        StringBuilder sql = new StringBuilder(buildQueryFilter(customerSearchRequest))
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem(CustomerSearchRequest customerSearchRequest) {
        String sql = buildQueryFilter(customerSearchRequest);
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

    private String buildQueryFilter(CustomerSearchRequest customerSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer c\n");
        setJoin(customerSearchRequest, sql);
        StringBuilder where = new StringBuilder(" WHERE c.is_active = 1 ");
        queryNormal(customerSearchRequest, where);
        querySpecial(customerSearchRequest, where);
        where.append("GROUP BY c.id");
        sql.append(where);
        return sql.toString();
    }
}
