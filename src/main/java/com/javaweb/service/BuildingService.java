package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface BuildingService {
    public ResponseDTO listStaffs(Long buildingId);
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    public void addOrUpdateBuilding(BuildingDTO buildingDTO);
    public void deleteBuilding(List<Long> ids);
    public BuildingDTO findByIdOfUpdate(Long id);
}
