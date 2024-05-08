package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.RoleEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.RoleDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BuildingRepository buildingRepository;
//    private DistrictCode districtCode = DistrictCode.
    public BuildingSearchResponse convertToResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse result = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        List<RentAreaEntity> listRentArea = buildingEntity.getRentAreaEntities();
        String rentArea = listRentArea.stream().map(it -> it.getValue() + "").collect(Collectors.joining(","));
        String district = "";
        if(buildingEntity.getDistrict() != null) district = DistrictCode.districtCodes().get(buildingEntity.getDistrict());
        result.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + district);
        result.setRentArea(rentArea);
        return result;
    }
    public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
        BuildingEntity result = modelMapper.map(buildingDTO, BuildingEntity.class);
        BuildingEntity tmp = null;
        if(buildingDTO.getId() != null) tmp = buildingRepository.findById(buildingDTO.getId()).get();
        result.setUsers(tmp.getUsers());

        List<String> listTypeCode = buildingDTO.getTypeCode();
        String typeCode = listTypeCode.stream().map(it -> it).collect(Collectors.joining(","));
        result.setType(typeCode);
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        String[] listRentArea = buildingDTO.getRentArea().split(",\\s*");
        if(StringUtils.check(listRentArea[0])){
            for(String it: listRentArea){
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(Long.parseLong(it));
                rentAreaEntity.setBuildingEntity(result);
                rentAreaEntities.add(rentAreaEntity);
            }
            result.setRentAreaEntities(rentAreaEntities);
        }
        return result;
    }
    public BuildingDTO convertToDto(BuildingEntity buildingEntity) {
        BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<String> typeCode = new ArrayList<>();
        String[] typeCode_tmp = buildingEntity.getType().split(",");
        for(String it: typeCode_tmp) typeCode.add(it);
        result.setTypeCode(typeCode);
        return result;
    }
}
