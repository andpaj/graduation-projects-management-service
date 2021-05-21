package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.ApplicationDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.applicationRest.ApplicationRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ApplicationService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ApplicationDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    final
    ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/{applicationId}")
    public ApplicationRest getApplicationById(@PathVariable String applicationId){

        ModelMapper modelMapper = new ModelMapper();
        ApplicationDto applicationDto = applicationService.getApplicationByApplicationId(applicationId);

        ApplicationRest applicationRest = modelMapper.map(applicationDto, ApplicationRest.class);

        return applicationRest;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    @PostMapping(path = "/create")
    public ApplicationRest createApplication(@Valid
                                                @RequestParam String projectId,
                                              @RequestParam String teamId,
                                              @RequestBody ApplicationDetailsRequestModel applicationDetails){

        ModelMapper modelMapper = new ModelMapper();
        ApplicationDto applicationDto = modelMapper.map(applicationDetails, ApplicationDto.class);
        ApplicationDto savedApplication = applicationService.createApplication(projectId, teamId, applicationDto);

        ApplicationRest applicationRest = modelMapper.map(savedApplication, ApplicationRest.class);

        return applicationRest;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/getAllByUserId/{userId}")
    public List<ApplicationRest> getAllApplicationsByUserId(@PathVariable String userId){
        ModelMapper modelMapper = new ModelMapper();
        List<ApplicationRest> applicationRestList = new ArrayList<>();
        List<ApplicationDto> applicationDtoList = applicationService.getAllApplicationsByUserId(userId);

        for (ApplicationDto applicationDto: applicationDtoList){
            ApplicationRest applicationRest = modelMapper.map(applicationDto, ApplicationRest.class);
            applicationRestList.add(applicationRest);
        }

        return applicationRestList;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/getAllBySupervisorId/{supervisorId}")
    public List<ApplicationRest> getAllApplicationsBySupervisorId(@PathVariable String supervisorId){

        ModelMapper modelMapper = new ModelMapper();
        List<ApplicationRest> applicationRestList = new ArrayList<>();
        List<ApplicationDto> applicationDtoList = applicationService.getAllApplicationsBySupervisorId(supervisorId);

        for (ApplicationDto applicationDto: applicationDtoList){
            ApplicationRest applicationRest = modelMapper.map(applicationDto, ApplicationRest.class);
            applicationRestList.add(applicationRest);
        }

        return applicationRestList;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN') or #supervisorId == principal.userId")
    @PostMapping(path = "/acceptBySupervisor")
    public ApplicationRest acceptApplicationFromSupervisorSide(@RequestParam String supervisorId,
                                                                @RequestParam String applicationId){

        ModelMapper modelMapper = new ModelMapper();
        ApplicationDto acceptedApplication = applicationService
                .acceptApplicationFromSupervisorSide(supervisorId, applicationId);

        ApplicationRest applicationRest = modelMapper.map(acceptedApplication, ApplicationRest.class);

        return applicationRest;


    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN') or #supervisorId == principal.userId")
    @PostMapping(path = "/declineBySupervisor")
    public ApplicationRest declineApplicationFromSupervisorSide(@RequestParam String supervisorId,
                                                                @RequestParam String applicationId){

        ModelMapper modelMapper = new ModelMapper();
        ApplicationDto acceptedApplication = applicationService
                .declineApplicationFromSupervisorSide(supervisorId, applicationId);

        ApplicationRest applicationRest = modelMapper.map(acceptedApplication, ApplicationRest.class);

        return applicationRest;


    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN') or #studentId == principal.userId")
    @PostMapping(path = "/acceptByStudent")
    public ApplicationRest acceptApplicationFromStudentSide(@RequestParam String studentId,
                                                                @RequestParam String applicationId){

        ModelMapper modelMapper = new ModelMapper();
        ApplicationDto acceptedApplication = applicationService
                .acceptApplicationFromStudentSide(studentId, applicationId);

        ApplicationRest applicationRest = modelMapper.map(acceptedApplication, ApplicationRest.class);

        return applicationRest;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN') or #studentId == principal.userId")
    @PostMapping(path = "/declineByStudent")
    public ApplicationRest declineApplicationFromStudentSide(@RequestParam String studentId,
                                                             @RequestParam String applicationId){

        ModelMapper modelMapper = new ModelMapper();
        ApplicationDto acceptedApplication = applicationService
                .declineApplicationFromStudentSide(studentId, applicationId);

        ApplicationRest applicationRest = modelMapper.map(acceptedApplication, ApplicationRest.class);

        return applicationRest;


    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN') or #userId == principal.userId")
    @DeleteMapping(path = "/delete")
    public String deleteApplication(@RequestParam String applicationId, @RequestParam String userId){

        applicationService.deleteApplication(applicationId);
        return applicationId;

    }

}
