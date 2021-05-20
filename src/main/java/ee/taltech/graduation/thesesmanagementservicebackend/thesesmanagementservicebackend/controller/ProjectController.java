package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.convertor.ProjectRequestToDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.ProjectDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.projectRest.ProjectRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ProjectService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ProjectDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    //Get

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/{projectId}")
    public ProjectRest getProject(@PathVariable String projectId){

        ModelMapper modelMapper = new ModelMapper();

        ProjectDto projectDto = projectService.getProjectByProjectId(projectId);

        ProjectRest projectRest = modelMapper.map(projectDto, ProjectRest.class);

        return projectRest;

    }



    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/allProjects")
    public List<ProjectRest> getAllProjects() {

        ModelMapper modelMapper = new ModelMapper();

        List<ProjectRest> returnValue = new ArrayList<>();

        List<ProjectDto> projectDtoList = projectService.getAllProjects();

        for (ProjectDto projectDto : projectDtoList) {

            ProjectRest projectRest = modelMapper.map(projectDto, ProjectRest.class);
            returnValue.add(projectRest);

        }

        return returnValue;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/projectsByUserId/{userId}")
    public List<ProjectRest> getProjectsByUserId(@PathVariable String userId){
        ModelMapper modelMapper = new ModelMapper();

        List<ProjectRest> returnValue = new ArrayList<>();

        List<ProjectDto> projectDtoList = projectService.getProjectsByUserId(userId);

        for (ProjectDto projectDto : projectDtoList) {

            ProjectRest projectRest = modelMapper.map(projectDto, ProjectRest.class);
            returnValue.add(projectRest);

        }

        return returnValue;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/coSupervisingProjects/{userId}")
    public List<ProjectRest> getCoSupervisingProjects(@PathVariable String userId){
        ModelMapper modelMapper = new ModelMapper();

        List<ProjectRest> returnValue = new ArrayList<>();

        List<ProjectDto> projectDtoList = projectService.getCoSupervisingProjects(userId);

        for (ProjectDto projectDto : projectDtoList) {

            ProjectRest projectRest = modelMapper.map(projectDto, ProjectRest.class);
            returnValue.add(projectRest);

        }

        return returnValue;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/projectsByGroupId/{groupId}")
    public List<ProjectRest> getProjectsByGroupId(@PathVariable String groupId){

        ModelMapper modelMapper = new ModelMapper();

        List<ProjectRest> returnValue = new ArrayList<>();

        List<ProjectDto> projectDtoList = projectService.getProjectsByGroupId(groupId);

        for (ProjectDto projectDto : projectDtoList) {

            ProjectRest projectRest = modelMapper.map(projectDto, ProjectRest.class);
            returnValue.add(projectRest);

        }

        return returnValue;


    }



    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @PostMapping(path = "/create")
    public ProjectRest createProject(@Valid @RequestParam String userId, @RequestBody ProjectDetailsRequestModel projectDetails) {

        ModelMapper modelMapper = new ModelMapper();

        List<String> groupsId = new ArrayList<>();
        if (projectDetails.getGroups() != null){
            groupsId = projectDetails.getGroups();
        }

        List<String> coSupervisors = new ArrayList<>();

        if (projectDetails.getCoSupervisors() != null){
            coSupervisors = projectDetails.getCoSupervisors();
        }

        ProjectDto projectDto = modelMapper.map(projectDetails, ProjectDto.class);

        ProjectDto createdThesis = projectService.createProject(userId, groupsId, coSupervisors, projectDto);

        ProjectRest projectRest = modelMapper.map(createdThesis, ProjectRest.class);

        return projectRest;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN') or #userId == principal.userId")
    @PutMapping(path = "/update")
    public ProjectRest updateProject(@RequestParam String projectId,
                                     @RequestParam String userId,
                                     @RequestBody ProjectDetailsRequestModel projectDetails){

        ModelMapper modelMapper = new ModelMapper();

        ProjectDto projectDto = modelMapper.map(projectDetails, ProjectDto.class);

        ProjectDto updatedProject = projectService.updateProject(projectId, projectDto);

        ProjectRest returnValue = modelMapper.map(updatedProject, ProjectRest.class);

        return returnValue;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN') or #userId == principal.userId")
    @DeleteMapping(path = "/delete")
    public String deleteProject(@RequestParam String projectId, @RequestParam String userId){

        projectService.deleteProjects(projectId);
        return projectId;
    }

}
