package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.ApplicationDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ApplicationRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ApplicationService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ApplicationDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/{id}")
    private ApplicationRest getApplicationById(@PathVariable String id){

        ModelMapper modelMapper = new ModelMapper();
        ApplicationDto applicationDto = applicationService.getApplicationByApplicationId(id);

        ApplicationRest applicationRest = modelMapper.map(applicationDto, ApplicationRest.class);

        return applicationRest;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PostMapping(path = "/create")
    private ApplicationRest createApplication(@RequestParam String projectId,
                                              @RequestParam String teamId,
                                              @RequestBody ApplicationDetailsRequestModel applicationDetails){

        ModelMapper modelMapper = new ModelMapper();
        ApplicationDto applicationDto = modelMapper.map(applicationDetails, ApplicationDto.class);
        ApplicationDto savedApplication = applicationService.createApplication(projectId, teamId, applicationDto);

        ApplicationRest applicationRest = modelMapper.map(savedApplication, ApplicationRest.class);

        return applicationRest;

    }






}
