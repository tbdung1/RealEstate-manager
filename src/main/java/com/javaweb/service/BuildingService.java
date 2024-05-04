package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    public ResponseDTO listStaffs(Long buildingId);
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
    public void addOrUpdateBuilding(BuildingDTO buildingDTO);
    public void deleteBuilding(List<Long> ids);
    public BuildingDTO findByIdOfUpdate(Long id);
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
    int countTotalItems(BuildingSearchRequest buildingSearchRequest);
}
