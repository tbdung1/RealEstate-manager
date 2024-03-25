package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    public List<BuildingEntity> findAllBuilding(BuildingSearchRequest buildingSearchRequest);
    List<BuildingEntity> getAllBuildings(Pageable pageable);

    int countTotalItem();
}
