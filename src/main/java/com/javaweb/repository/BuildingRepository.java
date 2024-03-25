package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RoleEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity,Long>, BuildingRepositoryCustom {
    public List<BuildingEntity> findByIdIn(List<Long> ids);
    public Page<BuildingEntity> findByName(String searchValue, Pageable pageable);
    public Page<BuildingEntity> findByNameContainingIgnoreCase(String searchValue, Pageable pageable);
}
