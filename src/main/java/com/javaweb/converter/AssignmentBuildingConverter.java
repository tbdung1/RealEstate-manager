package com.javaweb.converter;

//import com.javaweb.entity.AssignmentBuildingEntity;
//import com.javaweb.entity.BuildingEntity;
//import com.javaweb.entity.RoleEntity;
//import com.javaweb.entity.UserEntity;
//import com.javaweb.model.dto.AssignmentBuildingDTO;
//import com.javaweb.model.dto.RoleDTO;
//import com.javaweb.repository.BuildingRepository;
//import com.javaweb.repository.UserRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;

//@Component
public class AssignmentBuildingConverter {
//    @Autowired
//    private BuildingRepository buildingRepository;
//    @Autowired
//    private UserRepository userRepository;
//    public List<AssignmentBuildingEntity> convertToListEntity(AssignmentBuildingDTO assignmentBuildingDTO) {
//        List<AssignmentBuildingEntity> result = new ArrayList<>();
//        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
//        List<Long> listStaff = assignmentBuildingDTO.getStaffs();
//        for(Long it: listStaff){
//            UserEntity userEntity = userRepository.findById(it).get();
//            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
//            assignmentBuildingEntity.setUserEntity(userEntity);
//            assignmentBuildingEntity.setBuildingEntity(buildingEntity);
//            result.add(assignmentBuildingEntity);
//        }
//        return result;
//    }
}
