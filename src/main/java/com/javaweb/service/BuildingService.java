package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {
    public ResponseDTO listStaffs(Long buildingId);
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    public void addOrUpdateBuilding(BuildingDTO buildingDTO);
    public void deleteBuilding(List<Long> ids);
    public BuildingDTO findByIdOfUpdate(Long id);
    List<BuildingDTO> getBuildings(String searchValue, Pageable pageable);
    List<BuildingDTO> getAllBuildings(Pageable pageable);
    int countTotalItems();
}
