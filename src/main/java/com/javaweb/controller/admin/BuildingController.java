package com.javaweb.controller.admin;



import com.javaweb.enums.BuildingType;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    UserService userService;
    @GetMapping(value="/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch", buildingSearchRequest);
        List<BuildingSearchResponse> reponseList = new ArrayList<>();
        BuildingSearchResponse item1 = new BuildingSearchResponse();
        item1.setId(3L);
        item1.setAddress("Chung cu Topaz Home Block A");
        item1.setName("ACM Building");
        item1.setRentArea("100,200,300");
        item1.setFloorArea(64L);
        BuildingSearchResponse item2 = new BuildingSearchResponse();
        item2.setId(2L);
        item2.setAddress("Chung cu Topaz Home Block B");
        item2.setName("Nam Giao Building");
        item2.setRentArea("300");
        item2.setFloorArea(50L);
        reponseList.add(item1);
        reponseList.add(item2);
        mav.addObject("buildingList",reponseList);
        mav.addObject("listStaffs", userService.getStaffs());
        mav.addObject("districtCodes", DistrictCode.districtCodes());
        mav.addObject("typeCodes", BuildingType.type());
        return mav;
    }
    @GetMapping(value="/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districtCodes", DistrictCode.districtCodes());
        mav.addObject("typeCodes", BuildingType.type());
        return mav;
    }

    @GetMapping(value="/admin/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(id);
        buildingDTO.setName("Tran Ba Dung");
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districtCodes", DistrictCode.districtCodes());
        mav.addObject("typeCodes", BuildingType.type());
        return mav;
    }
}
