package com.javaweb.api.admin;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.*;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value="customerAPIOfAdmin")
@RequestMapping(value="/api/customer")
public class CustomerAPI {
    @Autowired
    ICustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ITransactionService transactionService;
    @PostMapping
    public void addOrUpdateCustomer(@RequestBody(required = false) CustomerDTO customerDTO){
        customerService.addOrUpdateCustomer(customerDTO);
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = customerService.listStaffs(id);
        return result;
    }
    @DeleteMapping("/{ids}")
    public void deleteCustomer(@PathVariable List<Long> ids){
        customerService.deleteCustomers(ids);
    }
    @PostMapping("/assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        customerService.updateAssignmentCustomer(assignmentCustomerDTO);
    }
    @PostMapping("/transaction")
    public void addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.addOrUpdateTransaction(transactionDTO);
    }
    @DeleteMapping("/transaction/{id}")
    public void deleteTransaction(@PathVariable Long id){ transactionService.deleteTransaction(id);}
}
