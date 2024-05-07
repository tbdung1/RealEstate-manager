package com.javaweb.controller.admin;



import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.BuildingType;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    UserService userService;
    @Autowired
    BuildingService buildingService;
    @Autowired
    MessageUtils messageUtils;
    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtils.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
    @GetMapping(value="/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute(SystemConstant.MODEL) BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch", buildingSearchRequest);
        List<BuildingSearchResponse> reponseList = new ArrayList<>();
        DisplayTagUtils.of(request, buildingSearchRequest);
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
            reponseList = buildingService.findAll(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()));
        }
        else {
            reponseList = buildingService.findAll(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()));
        }
        reponseList = buildingService.findAll(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()));
        BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
        buildingSearchResponse.setListResult(reponseList);
        buildingSearchResponse.setTotalItems(buildingService.countTotalItems(buildingSearchRequest));
//        mav.addObject(SystemConstant.MODEL, buildingSearchResponse);
        initMessageResponse(mav, request);
        mav.addObject("buildingList",buildingSearchResponse);
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
