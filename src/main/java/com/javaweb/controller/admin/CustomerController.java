package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.BuildingType;
import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchReponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value="customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    UserService userService;
    @Autowired
    ICustomerService customerService;
    @Autowired
    MessageUtils messageUtils;
    @Autowired
    ITransactionService transactionService;
    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtils.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
    @GetMapping(value="/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        CustomerSearchReponse customerSearchReponse = new CustomerSearchReponse();
        DisplayTagUtils.of(request, customerSearchRequest);
        List<CustomerSearchReponse> customerList = new ArrayList<>();
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
            customerList = customerService.findAll(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems()));
        }
        else {
            customerList = customerService.findAll(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems()));
        }
        customerSearchReponse.setListResult(customerList);
        customerSearchReponse.setTotalItems(customerService.countTotalItems(customerSearchRequest));
        initMessageResponse(mav, request);
        mav.addObject("modelSearch", customerSearchRequest);
        mav.addObject("customerList",customerSearchReponse);
        mav.addObject("listStaffs", userService.getStaffs());
        return mav;
    }
    @GetMapping(value="/admin/customer-edit")
    public ModelAndView customerEdit(@ModelAttribute("customerEdit") CustomerDTO customerDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        List<String> listStatus = new ArrayList<>();
        listStatus.add("Chưa xử lý");
        listStatus.add("Đang xử lý");
        listStatus.add("Đã xử lý");
        mav.addObject("listStatus", listStatus);
        return mav;
    }

    @GetMapping(value="/admin/customer-edit-{id}")
    public ModelAndView customerEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO = customerService.findByIdOfUpdate(id);
        List<String> listStatus = new ArrayList<>();
        listStatus.add("Chưa xử lý");
        listStatus.add("Đang xử lý");
        listStatus.add("Đã xử lý");
        mav.addObject("listStatus", listStatus);
        mav.addObject("customerEdit", customerDTO);
        mav.addObject("transactionTypes", TransactionType.type());

        List<TransactionDTO> transactionType1 = new ArrayList<>();
        List<TransactionDTO> transactionType2 = new ArrayList<>();
        for(String type: TransactionType.type().keySet()){
            List<TransactionDTO> transactionDTOList = transactionService.transactionTypeCustomer(type, id);
            if(type.equals("CSKH")) transactionType1 = transactionDTOList;
            else if(type.equals("DDX")) transactionType2 = transactionDTOList;
        }
        mav.addObject("typeCSKH", transactionType1);
        mav.addObject("typeDDX", transactionType2);
        return mav;
    }
}
