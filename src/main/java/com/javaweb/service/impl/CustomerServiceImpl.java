package com.javaweb.service.impl;

import com.javaweb.config.WebSecurityConfig;
import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.*;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchReponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.ICustomerService;
import com.javaweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerConverter customerConverter;
    @Autowired
    UserRepository userRepository;


    @Override
    public void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        List<UserEntity> userEntities = userRepository.findByIdIn(assignmentCustomerDTO.getStaffs());
        customerEntity.setUsers(userEntities);
        customerRepository.save(customerEntity);
    }

    @Override
    public ResponseDTO listStaffs(Long customerId) {
        ResponseDTO responseDTO = new ResponseDTO();
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignments = customerEntity.getUsers();

        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for(UserEntity it: staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if(staffAssignments.contains(it)) staffResponseDTO.setChecked("checked");
            else staffResponseDTO.setChecked("");
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public CustomerDTO findByIdOfUpdate(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        CustomerDTO customerDTO = customerConverter.convertToDTO(customerEntity);
        return customerDTO;
    }
    @Override
    public int countTotalItems(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.countTotalItem(customerSearchRequest);
    }

    @Override
    public void deleteCustomers(List<Long> ids) {
        List<CustomerEntity> customerEntities = customerRepository.findByIdIn(ids);
        for(CustomerEntity cus : customerEntities){
            cus.setIs_active(false);
        }
    }

    @Override
    public List<CustomerSearchReponse> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        List<CustomerEntity> customerEntities = customerRepository.findAllCustomer(customerSearchRequest, pageable);
        List<CustomerSearchReponse> result = new ArrayList<>();
        for(CustomerEntity customerEntity : customerEntities){
            result.add(customerConverter.convertToResponse(customerEntity));
        }
        return result;
    }

    @Override
    public void addOrUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        if(customerDTO.getId() != null){
            customerEntity = customerRepository.findById(customerDTO.getId()).get();
            customerDTO.setCreatedBy(customerEntity.getCreatedBy());
            customerDTO.setCreatedDate(customerEntity.getCreatedDate());
        }
        List<TransactionEntity> transactionEntities = customerEntity.getTransactionEntities();
        List<UserEntity> userEntities = customerEntity.getUsers();
        customerEntity = customerConverter.convertToEntity(customerDTO);
        if(transactionEntities != null) customerEntity.setTransactionEntities(transactionEntities);
        if(userEntities != null) customerEntity.setUsers(userEntities);
        customerRepository.save(customerEntity);
    }
}
