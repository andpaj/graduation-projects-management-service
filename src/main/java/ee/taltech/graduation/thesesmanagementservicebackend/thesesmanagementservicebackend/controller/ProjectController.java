package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.convertor.ProjectRequestToDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.ProjectDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ProjectRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ProjectService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectRequestToDto projectRequestToDto;

    @Autowired
    ProjectService projectService;

    //Get


    @GetMapping(path = "/{id}")
    public ProjectRest getProject(@PathVariable String id){

        ModelMapper modelMapper = new ModelMapper();

        ProjectDto projectDto = projectService.getProjectByProjectId(id);

        ProjectRest projectRest = modelMapper.map(projectDto, ProjectRest.class);

        return projectRest;

    }

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


    @GetMapping(path = "/projectsByUserId/{id}")
    public List<ProjectRest> getProjectsByUserId(@PathVariable String id){
        ModelMapper modelMapper = new ModelMapper();

        List<ProjectRest> returnValue = new ArrayList<>();

        List<ProjectDto> projectDtoList = projectService.getProjectsByUserId(id);

        for (ProjectDto projectDto : projectDtoList) {

            ProjectRest projectRest = modelMapper.map(projectDto, ProjectRest.class);
            returnValue.add(projectRest);

        }

        return returnValue;

    }

    @PostMapping(path = "/create/{id}")
    public ProjectRest createProject(@PathVariable String id, @RequestBody ProjectDetailsRequestModel projectDetails) {

        ModelMapper modelMapper = new ModelMapper();

        //ThesisDto thesisDto = thesisRequestToDto.convert(thesisDetails);
        ProjectDto projectDto = modelMapper.map(projectDetails, ProjectDto.class);

        ProjectDto createdThesis = projectService.createProject(id, projectDto);

        ProjectRest projectRest = modelMapper.map(createdThesis, ProjectRest.class);

        return projectRest;


    }

    @PutMapping(path = "/update/{id}")
    public ProjectRest updateProject(@PathVariable String id, @RequestBody ProjectDetailsRequestModel projectDetails){

        ModelMapper modelMapper = new ModelMapper();

        ProjectDto projectDto = modelMapper.map(projectDetails, ProjectDto.class);

        ProjectDto updatedProject = projectService.updateProject(id, projectDto);

        ProjectRest returnValue = modelMapper.map(updatedProject, ProjectRest.class);

        return returnValue;

    }


    @DeleteMapping(path = "/delete/{id}")
    public String deleteProject(@PathVariable String id){

        projectService.deleteProjects(id);
        return id;
    }


    @PostMapping(path = "/apply")
    public String applyForProject(){

        return "accept thesis was called";
    }




}
