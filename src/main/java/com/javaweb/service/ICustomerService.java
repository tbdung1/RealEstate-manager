package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.*;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchReponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
    public List<CustomerSearchReponse> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    public void addOrUpdateCustomer(CustomerDTO customerDTO);
    public CustomerDTO findByIdOfUpdate(Long id);
    int countTotalItems(CustomerSearchRequest customerSearchRequest);
    public void deleteCustomers (List<Long> ids);
    public ResponseDTO listStaffs(Long customerId);
    public void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO);

}
