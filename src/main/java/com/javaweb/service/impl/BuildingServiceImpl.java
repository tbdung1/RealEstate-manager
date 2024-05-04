package com.javaweb.service.impl;

import com.javaweb.converter.AssignmentBuildingConverter;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.*;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
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
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BuildingConverter buildingConverter;
    @Autowired
    RentAreaRepository rentAreaRepository;
    @Autowired
    UploadFileUtils uploadFileUtils;
    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }
    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignments = buildingEntity.getUsers();

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

//    @Override
//    public List<BuildingDTO> getBuildings(String searchValue, Pageable pageable) {
//        Page<BuildingEntity> buildings = null;
//        if (org.apache.commons.lang.StringUtils.isNotBlank(searchValue)) {
//            buildings = buildingRepository.findByNameContainingIgnoreCase(searchValue, pageable);
//        } else {
//            buildings = buildingRepository.findAll(pageable);
//        }
//        List<BuildingEntity> newsEntities = buildings.getContent();
//        List<BuildingDTO> result = new ArrayList<>();
//        for (BuildingEntity buildingEntity : newsEntities) {
//            BuildingDTO buildingDTO = buildingConverter.convertToDto(buildingEntity);
//            result.add(buildingDTO);
//        }
//        return result;
//    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        List<BuildingEntity> buildingEntities = buildingRepository.findAllBuilding(buildingSearchRequest, pageable);
        for (BuildingEntity x : buildingEntities){
            BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
            buildingSearchResponse = buildingConverter.convertToResponse(x);
            buildingSearchResponses.add(buildingSearchResponse);
        }
        return  buildingSearchResponses;
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = new BuildingEntity();
        if(buildingDTO.getId() != null) {
            buildingEntity = buildingRepository.findById(buildingDTO.getId()).get();
//            List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreaEntities();
//            rentAreaRepository.deleteInBatch(rentAreaEntities);
        }
        buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        saveThumbnail(buildingDTO, buildingEntity);
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        String[] listRentArea = buildingDTO.getRentArea().split(",\\s*");
        if(StringUtils.check(listRentArea[0])){
            for(String it: listRentArea){
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(Long.parseLong(it));
                rentAreaEntities.add(rentAreaEntity);
            }
            buildingEntity.setRentAreaEntities(rentAreaEntities);
        }
        buildingRepository.save(buildingEntity);
    }

    @Override
    public void deleteBuilding(List<Long> ids) {
        List<BuildingEntity> buildingEntities = buildingRepository.findByIdIn(ids);
//        for(BuildingEntity it : buildingEntities){
//            buildingRepository.delete(it);
//        }
        buildingRepository.deleteInBatch(buildingEntities);
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
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List<UserEntity> userEntities = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
        buildingEntity.setUsers(userEntities);
        buildingRepository.save(buildingEntity);
//        List<AssignmentBuildingEntity> assignmentBuildingEntities = buildingEntity.getAssignmentBuildingEntities();
//        assignmentBuildingRepository.deleteInBatch(assignmentBuildingEntities);

//        List<AssignmentBuildingEntity> result = assignmentBuildingConverter.convertToListEntity(assignmentBuildingDTO);
//        assignmentBuildingRepository.saveAll(result);
    }

    @Override
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countTotalItem(buildingSearchRequest);
    }

}
