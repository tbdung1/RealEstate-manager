package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignments = new ArrayList<>();
        List<AssignmentBuildingEntity> assignmentBuildingEntities = buildingEntity.getAssignmentBuildingEntityEntities();
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
}
