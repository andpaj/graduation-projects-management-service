package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.ApplicationDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ApplicationRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ApplicationService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ApplicationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @PostMapping("/create")
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
