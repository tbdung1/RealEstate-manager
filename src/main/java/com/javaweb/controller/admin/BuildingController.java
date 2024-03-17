package com.javaweb.controller.admin;



import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.BuildingType;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
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
    @Autowired
    BuildingService buildingService;
    @GetMapping(value="/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch", buildingSearchRequest);

        List<BuildingSearchResponse> reponseList = new ArrayList<>();
        reponseList = buildingService.findAll(buildingSearchRequest);

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
        BuildingDTO buildingDTO = buildingService.findByIdOfUpdate(id);
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districtCodes", DistrictCode.districtCodes());
        mav.addObject("typeCodes", BuildingType.type());
        return mav;
    }
}
