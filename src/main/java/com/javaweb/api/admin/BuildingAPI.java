package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value="buildingAPIOfAdmin")
@RequestMapping(value="/api/building")
public class BuildingAPI {
    @Autowired
    BuildingService buildingService;
    @PostMapping
    public void addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingService.addOrUpdateBuilding(buildingDTO);
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids){
        buildingService.deleteBuilding(ids);
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
    }

    @PostMapping("/assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        System.out.println("ok");
    }
}
