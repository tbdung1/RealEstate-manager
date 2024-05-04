package com.javaweb.converter;

import com.javaweb.config.WebSecurityConfig;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchReponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {
    @Autowired
    ModelMapper modelMapper;
    public CustomerSearchReponse convertToResponse(CustomerEntity customerEntity) {
        CustomerSearchReponse result = modelMapper.map(customerEntity, CustomerSearchReponse.class);
        return result;
    }
    public CustomerEntity convertToEntity(CustomerDTO customerDTO) {
        CustomerEntity result = modelMapper.map(customerDTO, CustomerEntity.class);
        result.setFullName(customerDTO.getName());
        result.setPhone(customerDTO.getCustomerPhone());
        result.setIs_active(true);
        return result;
    }
    public CustomerDTO convertToDTO(CustomerEntity customerEntity){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        CustomerDTO result = modelMapper.map(customerEntity, CustomerDTO.class);
        result.setName(customerEntity.getFullName());
        result.setCustomerPhone(customerEntity.getPhone());
        return result;
    }
}
