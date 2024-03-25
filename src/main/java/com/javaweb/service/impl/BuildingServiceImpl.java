package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BuildingConverter buildingConverter;
    @Autowired
    RentAreaRepository rentAreaRepository;
    @Autowired
    AssignmentBuildingRepository assignmentBuildingRepository;
    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignments = new ArrayList<>();
        List<AssignmentBuildingEntity> assignmentBuildingEntities = buildingEntity.getAssignmentBuildingEntities();
        for(AssignmentBuildingEntity item : assignmentBuildingEntities){
            UserEntity userEntity = item.getUserEntity();
            if(staffAssignments.contains(userEntity) == false) staffAssignments.add(userEntity);
        }
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
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        List<BuildingEntity> buildingEntities = buildingRepository.findAllBuilding(buildingSearchRequest);
        for (BuildingEntity x : buildingEntities){
            BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
            buildingSearchResponse = buildingConverter.convertToResponse(x);
            buildingSearchResponses.add(buildingSearchResponse);
        }
        return buildingSearchResponses;
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        List<AssignmentBuildingEntity> assignmentBuildingEntities = buildingEntity.getAssignmentBuildingEntities();
        if(buildingDTO.getId() != null) {
            List<Long> ids = new ArrayList<>();
            ids.add(buildingDTO.getId());
            deleteBuilding(ids);
        }
        String tmp = buildingDTO.getRentArea();
        String[] listRentArea = tmp.split(",\\s*");
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        if(StringUtils.check(listRentArea[0])){
            for(String it: listRentArea){
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setBuildingEntity(buildingEntity);
                rentAreaEntity.setValue(Long.parseLong(it));
                rentAreaEntities.add(rentAreaEntity);
            }
        }
        buildingRepository.save(buildingEntity);
        if(rentAreaEntities != null) rentAreaRepository.saveAll(rentAreaEntities);
        if(assignmentBuildingEntities != null) assignmentBuildingRepository.saveAll(assignmentBuildingEntities);
    }

    @Override
    public void deleteBuilding(List<Long> ids) {
        List<BuildingEntity> buildingEntities = buildingRepository.findByIdIn(ids);
        for(BuildingEntity it : buildingEntities){
            List<RentAreaEntity> rentAreaEntities = it.getRentAreaEntities();
            rentAreaRepository.deleteInBatch(rentAreaEntities);
            List<AssignmentBuildingEntity> assignmentBuildingEntities = it.getAssignmentBuildingEntities();
            assignmentBuildingRepository.deleteInBatch(assignmentBuildingEntities);
            buildingRepository.delete(it);
        }
    }

    @Override
    public BuildingDTO findByIdOfUpdate(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingConverter.convertToDto(buildingEntity);
        List<RentAreaEntity> listRentArea = buildingEntity.getRentAreaEntities();
        if(listRentArea != null){
            String rentArea = listRentArea.stream().map(it -> it.getValue() + "").collect(Collectors.joining(","));
            buildingDTO.setRentArea(rentArea);
        }
        return buildingDTO;
    }

    @Override
    public List<BuildingDTO> getBuildings(String searchValue, Pageable pageable) {
        Page<BuildingEntity> buildings = null;
        if (org.apache.commons.lang.StringUtils.isNotBlank(searchValue)) {
            buildings = buildingRepository.findByNameContainingIgnoreCase(searchValue, pageable);
        } else {
            buildings = buildingRepository.findAll(pageable);
        }
        List<BuildingEntity> newsEntities = buildings.getContent();
        List<BuildingDTO> result = new ArrayList<>();
        for (BuildingEntity buildingEntity : newsEntities) {
            BuildingDTO buildingDTO = buildingConverter.convertToDto(buildingEntity);
            result.add(buildingDTO);
        }
        return result;
    }

    @Override
    public List<BuildingDTO> getAllBuildings(Pageable pageable) {
        List<BuildingEntity> buildingEntities = buildingRepository.getAllBuildings(pageable);
        List<BuildingDTO> results = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingDTO buildingDTO = buildingConverter.convertToDto(buildingEntity);
            results.add(buildingDTO);
        }
        return results;
    }

    @Override
    public int countTotalItems() {
        return buildingRepository.countTotalItem();
    }


}
